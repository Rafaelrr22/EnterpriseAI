package com.enterpriseai.vector.client;

import com.enterpriseai.vector.dto.VectorPoint;

public interface QdrantClient {

    void createCollection();

    void upsertPoint(VectorPoint point);

}