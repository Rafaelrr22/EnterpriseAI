package com.enterpriseai.conversation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ConversationResponse {

    private UUID id;
    private String title;

}