package com.enterpriseai.ai.client;

import com.enterpriseai.ai.dto.OllamaRequest;
import com.enterpriseai.ai.dto.OllamaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
public class OllamaClientImpl implements OllamaClient {

    private final RestClient restClient;

    @Value("${ollama.base-url}")
    private String baseUrl;

    @Override
    public OllamaResponse generate(OllamaRequest request){

        return restClient.post()
                .uri(baseUrl + "/api/generate")
                .body(request)
                .retrieve()
                .body(OllamaResponse.class);

    }
}
