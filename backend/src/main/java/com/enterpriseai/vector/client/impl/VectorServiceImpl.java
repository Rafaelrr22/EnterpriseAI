package com.enterpriseai.vector.service.impl;

import com.enterpriseai.vector.client.QdrantClient;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VectorServiceImpl implements VectorService {

    private final QdrantClient qdrantClient;

    @Override
    public void initializeCollection() {

        qdrantClient.createCollection();
    }
}