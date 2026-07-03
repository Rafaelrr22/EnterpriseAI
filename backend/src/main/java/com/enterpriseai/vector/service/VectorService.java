package com.enterpriseai.vector.service;

import com.enterpriseai.vector.dto.SearchResult;

import java.util.List;
import java.util.UUID;

public interface VectorService {

    void initializeCollection();

    void store(
            UUID documentId,
            UUID userId,
            String content,
            List<Double> embedding
    );

    void deleteByDocumentId(UUID documentId);

    List<SearchResult> search(
            String query,
            UUID userId
    );

}