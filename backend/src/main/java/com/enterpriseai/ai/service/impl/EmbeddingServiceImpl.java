package com.enterpriseai.ai.service.impl;

import com.enterpriseai.ai.client.OllamaClient;
import com.enterpriseai.ai.dto.EmbeddingRequest;
import com.enterpriseai.ai.dto.EmbeddingResponse;
import com.enterpriseai.ai.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingServiceImpl implements EmbeddingService {

    private final OllamaClient ollamaClient;

    @Value("${ollama.embedding-model}")
    private String embeddingModel;

    @Override
    public List<Double> generateEmbedding(String text) {

        EmbeddingRequest request =
                new EmbeddingRequest(embeddingModel, text);

        EmbeddingResponse response =
                ollamaClient.embeddings(request);

        return response.getEmbedding();
    }
}