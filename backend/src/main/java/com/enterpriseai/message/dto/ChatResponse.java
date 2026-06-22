package com.enterpriseai.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatResponse {

    private MessageResponse userMessage;
    private MessageResponse assistantMessage;

}