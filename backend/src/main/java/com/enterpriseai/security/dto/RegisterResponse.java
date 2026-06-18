package com.enterpriseai.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RegisterResponse {

    private UUID id;
    private String fullName;
    private String email;

}