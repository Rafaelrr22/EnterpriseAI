package com.enterpriseai.ai.controller;

import com.enterpriseai.ai.dto.AiPromptRequest;
import com.enterpriseai.ai.service.AiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/test")
    public String test(
            @Valid @RequestBody AiPromptRequest request
    ) {
        return aiService.generateResponse(request.getPrompt());
    }

}