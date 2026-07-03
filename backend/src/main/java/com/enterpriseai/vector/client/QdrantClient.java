package com.enterpriseai.vector.client;

import com.enterpriseai.vector.dto.SearchResult;
import com.enterpriseai.vector.dto.VectorPoint;

import java.util.List;
import java.util.UUID;

public interface QdrantClient {

    void createCollection();

    void upsertPoint(VectorPoint point);

    void deletePointsByDocumentId(UUID documentId);

    List<SearchResult> search(
            List<Double> embedding,
            UUID userId
    );

}