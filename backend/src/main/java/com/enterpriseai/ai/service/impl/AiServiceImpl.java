package com.enterpriseai.ai.service.impl;


import com.enterpriseai.ai.client.OllamaClient;
import com.enterpriseai.ai.dto.OllamaRequest;
import com.enterpriseai.ai.dto.OllamaResponse;
import com.enterpriseai.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final OllamaClient ollamaClient;

    @Value("${ollama.chat-model}")
    private String model;

    @Override
    public String generateResponse(String prompt) {

        System.out.println(">>> Sending prompt to Ollama...");

        OllamaRequest request = new OllamaRequest(
                model,
                prompt,
                false
        );

        OllamaResponse response = ollamaClient.generate(request);

        System.out.println(">>> Ollama answered!");

        return response.getResponse();
    }

}
