package com.enterpriseai.document.service.impl;

import com.enterpriseai.common.security.AuthenticatedUserService;
import com.enterpriseai.document.dto.DocumentResponse;
import com.enterpriseai.document.entity.Document;
import com.enterpriseai.document.repository.DocumentRepository;
import com.enterpriseai.document.service.DocumentService;
import com.enterpriseai.document.storage.FileStorage;
import com.enterpriseai.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final FileStorage fileStorage;

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

}