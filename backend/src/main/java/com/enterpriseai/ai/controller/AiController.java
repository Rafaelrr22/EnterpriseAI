package com.enterpriseai.ai.controller;

import com.enterpriseai.ai.dto.AiPromptRequest;
import com.enterpriseai.ai.service.AiService;
import com.enterpriseai.ai.service.EmbeddingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;
    private final EmbeddingService embeddingService;

    @PostMapping("/test")
    public String test(
            @Valid @RequestBody AiPromptRequest request
    ) {
        return aiService.generateResponse(request.getPrompt());
    }

    @PostMapping("/embedding-test")
    public List<Double> embeddingTest(
            @Valid @RequestBody AiPromptRequest request
    ) {
        return embeddingService.generateEmbedding(
                request.getPrompt()
        );
    }

}