package com.enterpriseai.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MessageResponse {

    private UUID id;
    private String role;
    private String content;

}