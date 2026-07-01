package com.enterpriseai.rag.service.impl;

import com.enterpriseai.ai.service.AiService;
import com.enterpriseai.document.entity.Document;
import com.enterpriseai.document.repository.DocumentRepository;
import com.enterpriseai.rag.service.RagService;
import com.enterpriseai.vector.dto.SearchResult;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RagServiceImpl implements RagService {

    private final VectorService vectorService;
    private final AiService aiService;
    private final DocumentRepository documentRepository;

    @Override
    public String ask(String question) {

        List<SearchResult> chunks =
                vectorService.search(question);

        StringBuilder context = new StringBuilder();

        for (SearchResult chunk : chunks) {

            Document document = documentRepository.findById(
                    UUID.fromString(chunk.getDocumentId())
            ).orElse(null);

            String filename =
                    document != null
                            ? document.getFilename()
                            : "Unknown document";

            context.append("""
                    Document:
                    %s

                    %s

                    ----------------------------

                    """.formatted(
                    filename,
                    chunk.getContent()
            ));
        }

        String prompt = """
                You are an AI assistant that answers questions ONLY using the provided context.

                If the answer cannot be found in the context, reply:
                "I could not find that information in the uploaded documents."

                Context:
                %s

                Question:
                %s

                Answer:
                """.formatted(
                context.toString(),
                question
        );

        System.out.println(prompt);

        return aiService.generateResponse(prompt);
    }
}