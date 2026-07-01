package com.enterpriseai.vector.service;

import java.util.List;
import java.util.UUID;

public interface VectorService {

    void initializeCollection();

    void store(
            UUID documentId,
            String content,
            List<Double> embedding
    );

    void deleteByDocumentId(UUID documentId);

    List<String> search(String query);

}