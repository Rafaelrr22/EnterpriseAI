package com.enterpriseai.document.service.impl;

import com.enterpriseai.ai.service.EmbeddingService;
import com.enterpriseai.common.security.AuthenticatedUserService;
import com.enterpriseai.document.chunk.DocumentChunker;
import com.enterpriseai.document.dto.DocumentResponse;
import com.enterpriseai.document.entity.Document;
import com.enterpriseai.document.parser.DocumentParser;
import com.enterpriseai.document.repository.DocumentRepository;
import com.enterpriseai.document.service.DocumentService;
import com.enterpriseai.document.storage.FileStorage;
import com.enterpriseai.user.entity.User;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final FileStorage fileStorage;
    private final DocumentParser documentParser;
    private final DocumentChunker documentChunker;
    private final EmbeddingService embeddingService;
    private final VectorService vectorService;

    @Override
    @Transactional
    public DocumentResponse upload(MultipartFile file) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty.");
        }

        String filePath;

        try {
            filePath = fileStorage.store(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }

        String extractedText = documentParser.parse(new File(filePath));

        List<String> chunks = documentChunker.chunk(extractedText);

        for (String chunk : chunks) {

            List<Double> embedding =
                    embeddingService.generateEmbedding(chunk);

            vectorService.store(
                    chunk,
                    embedding
            );
        }

        User user = authenticatedUserService.getCurrentUser();

        Document document = Document.builder()
                .filename(file.getOriginalFilename())
                .storePath(filePath)
                .contentType(file.getContentType())
                .size(file.getSize())
                .uploadedAt(Instant.now())
                .uploadedBy(user)
                .build();

        Document savedDocument = documentRepository.save(document);

        return toResponse(savedDocument);
    }

    @Override
    public List<DocumentResponse> list() {

        User user = authenticatedUserService.getCurrentUser();

        System.out.println("Current user: " + user.getId());
        System.out.println("Current email: " + user.getEmail());

        return documentRepository.findByUploadedBy(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private DocumentResponse toResponse(Document document) {

        return new DocumentResponse(
                document.getId(),
                document.getFilename(),
                document.getContentType(),
                document.getSize(),
                document.getUploadedAt()
        );
    }

    @Override
    @Transactional
    public void delete(UUID id) {

        User user = authenticatedUserService.getCurrentUser();

        Document document = documentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Document not found.")
                );

        System.out.println("Current user: " + user.getId());

        System.out.println("Document owner: " +
                document.getUploadedBy().getId());

        System.out.println(
                user.getId().equals(document.getUploadedBy().getId())
        );


        if (!document.getUploadedBy().getId().equals(user.getId())) {
            throw new SecurityException("You are not allowed to delete this document."); //Substituir
        }

        File file = new File(document.getStorePath());

        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Failed to delete file.");
        }

        documentRepository.delete(document);
    }
}