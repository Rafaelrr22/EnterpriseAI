package com.enterpriseai.ai.client;

import com.enterpriseai.ai.dto.OllamaRequest;
import com.enterpriseai.ai.dto.OllamaResponse;

public interface OllamaClient {

    OllamaResponse generate(OllamaRequest request);

}
