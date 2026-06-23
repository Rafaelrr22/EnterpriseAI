package com.enterpriseai.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmbeddingRequest {

    private String model;
    private String prompt;
}
