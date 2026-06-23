package com.enterpriseai.ai.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class EmbeddingResponse {

    private List<Double> embedding;
}
