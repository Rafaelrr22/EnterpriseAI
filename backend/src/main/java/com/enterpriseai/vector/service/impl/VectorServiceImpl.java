package com.enterpriseai.vector.service.impl;

import com.enterpriseai.ai.service.EmbeddingService;
import com.enterpriseai.vector.client.QdrantClient;
import com.enterpriseai.vector.dto.SearchResult;
import com.enterpriseai.vector.dto.VectorPoint;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VectorServiceImpl implements VectorService {

    private final QdrantClient qdrantClient;
    private final EmbeddingService embeddingService;

    @Override
    public void initializeCollection() {

        qdrantClient.createCollection();

    }

    @Override
    public void store(
            UUID documentId,
            UUID userId,
            String content,
            List<Double> embedding
    ) {

        VectorPoint point = new VectorPoint(
                UUID.randomUUID().toString(),
                embedding,
                content,
                documentId.toString(),
                userId.toString()
        );

        qdrantClient.upsertPoint(point);

    }

    @Override
    public void deleteByDocumentId(UUID documentId) {

        qdrantClient.deletePointsByDocumentId(documentId);

    }

    @Override
    public List<SearchResult> search(
            String query,
            UUID userId
    ) {

        List<Double> embedding =
                embeddingService.generateEmbedding(query);

        return qdrantClient.search(
                embedding,
                userId
        );

    }

}