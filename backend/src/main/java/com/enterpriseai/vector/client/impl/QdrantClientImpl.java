package com.enterpriseai.vector.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

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
}