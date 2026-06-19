package com.enterpriseai.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OllamaRequest {

    private String model;
    private String prompt;
    private boolean stream;

}