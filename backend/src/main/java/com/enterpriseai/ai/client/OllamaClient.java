package com.enterpriseai.ai.client;

import com.enterpriseai.ai.dto.EmbeddingRequest;
import com.enterpriseai.ai.dto.EmbeddingResponse;
import com.enterpriseai.ai.dto.OllamaRequest;
import com.enterpriseai.ai.dto.OllamaResponse;

public interface OllamaClient {

    OllamaResponse generate(OllamaRequest request);

    EmbeddingResponse embeddings(EmbeddingRequest request);

}
