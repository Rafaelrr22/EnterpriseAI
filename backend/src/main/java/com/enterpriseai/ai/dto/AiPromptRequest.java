package com.enterpriseai.ai.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiPromptRequest {

    @NotBlank
    private String prompt;
}
