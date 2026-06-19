package com.enterpriseai.conversation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateConversationRequest {

    @NotBlank
    private String title;

}