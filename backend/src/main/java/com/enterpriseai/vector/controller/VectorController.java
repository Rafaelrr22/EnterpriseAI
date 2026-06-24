package com.enterpriseai.vector.controller;

import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vector")
@RequiredArgsConstructor
public class VectorController {

    private final VectorService vectorService;

    @PostMapping("/init")
    public String initialize() {

        vectorService.initializeCollection();

        return "Collection created.";
    }
}