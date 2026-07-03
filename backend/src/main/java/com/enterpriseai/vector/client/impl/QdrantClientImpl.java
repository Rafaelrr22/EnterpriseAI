package com.enterpriseai.vector.client.impl;

import com.enterpriseai.vector.client.QdrantClient;
import com.enterpriseai.vector.dto.SearchResult;
import com.enterpriseai.vector.dto.VectorPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
                                        "content", point.getContent(),
                                        "documentId", point.getDocumentId(),
                                        "userId", point.getUserId()
                                )
                        )
                }
        );

        restClient.put()
                .uri(baseUrl + "/collections/"
                        + collectionName
                        + "/points")
                .body(requestBody)
                .retrieve()
                .toBodilessEntity();

    }

    @Override
    public void deletePointsByDocumentId(UUID documentId) {

        Map<String, Object> requestBody = Map.of(
                "filter", Map.of(
                        "must", List.of(
                                Map.of(
                                        "key", "documentId",
                                        "match", Map.of(
                                                "value",
                                                documentId.toString()
                                        )
                                )
                        )
                )
        );

        restClient.post()
                .uri(baseUrl + "/collections/"
                        + collectionName
                        + "/points/delete")
                .body(requestBody)
                .retrieve()
                .toBodilessEntity();

    }

    @Override
    public List<SearchResult> search(
            List<Double> embedding,
            UUID userId
    ) {

        Map<String, Object> requestBody = Map.of(
                "vector", embedding,
                "limit", 3,
                "with_payload", true,
                "filter", Map.of(
                        "must", List.of(
                                Map.of(
                                        "key", "userId",
                                        "match", Map.of(
                                                "value",
                                                userId.toString()
                                        )
                                )
                        )
                )
        );

        Map response = restClient.post()
                .uri(baseUrl + "/collections/"
                        + collectionName
                        + "/points/search")
                .body(requestBody)
                .retrieve()
                .body(Map.class);

        List<SearchResult> results = new ArrayList<>();

        List<Map<String, Object>> points =
                (List<Map<String, Object>>) response.get("result");

        for (Map<String, Object> point : points) {

            Map<String, Object> payload =
                    (Map<String, Object>) point.get("payload");

            Object documentId = payload.get("documentId");

            results.add(
                    new SearchResult(
                            payload.get("content").toString(),
                            documentId != null
                                    ? documentId.toString()
                                    : null
                    )
            );

        }

        return results;

    }

}