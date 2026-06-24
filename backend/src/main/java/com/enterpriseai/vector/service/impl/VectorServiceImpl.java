package com.enterpriseai.vector.service.impl;

import com.enterpriseai.vector.client.QdrantClient;
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

    @Override
    public void initializeCollection() {

        qdrantClient.createCollection();
    }

    @Override
    public void store(
            String content,
            List<Double> embedding
    ) {

        VectorPoint point = new VectorPoint(
                UUID.randomUUID().toString(),
                embedding,
                content
        );

        System.out.println(">>> Storing vector");

        qdrantClient.upsertPoint(point);
    }


}