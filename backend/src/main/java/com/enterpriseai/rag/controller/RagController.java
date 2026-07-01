package com.enterpriseai.rag.controller;

import com.enterpriseai.rag.dto.RagRequest;
import com.enterpriseai.rag.dto.RagResponse;
import com.enterpriseai.rag.service.RagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
@RequiredArgsConstructor
public class RagController {

    private final RagService ragService;

    @PostMapping("/ask")
    public RagResponse ask(
            @RequestBody RagRequest request
    ) {

        return ragService.ask(
                request.getQuestion()
        );
    }
}