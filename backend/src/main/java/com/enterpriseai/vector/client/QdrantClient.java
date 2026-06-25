package com.enterpriseai.vector.client;

import com.enterpriseai.vector.dto.VectorPoint;

import java.util.List;

public interface QdrantClient {

    void createCollection();

    void upsertPoint(VectorPoint point);

    List<String> search(List<Double> embedding);

}