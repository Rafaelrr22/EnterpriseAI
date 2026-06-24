package com.enterpriseai.vector.client.impl;

import com.enterpriseai.vector.client.QdrantClient;
import com.enterpriseai.vector.dto.VectorPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class QdrantClientImpl implements QdrantClient {

    private final RestClient restClient;

    @Value("${qdrant.base-url}")
    private String baseUrl;

    @Value("${qdrant.collection-name}")
    private String collectionName;

    @Override
    public void createCollection() {

        String body = """
            {
              "vectors": {
                "size": 768,
                "distance": "Cosine"
              }
            }
            """;

        restClient.put()
                .uri(baseUrl + "/collections/" + collectionName)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void upsertPoint(VectorPoint point) {

        Map<String, Object> requestBody = Map.of(
                "points",
                new Object[]{
                        Map.of(
                                "id", point.getId(),
                                "vector", point.getVector(),
                                "payload", Map.of(
                                        "content", point.getContent()
                                )
                        )
                }
        );

        System.out.println(">>> Sending point to Qdrant");

        restClient.put()
                .uri(baseUrl + "/collections/" +
                        collectionName +
                        "/points")
                .body(requestBody)
                .retrieve()
                .toBodilessEntity();
    }
}