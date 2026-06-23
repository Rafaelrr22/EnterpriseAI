package com.enterpriseai.document.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class DocumentResponse {

    private UUID id;
    private String filename;
    private String contentType;
    private Long size;
    private Instant uploadedAt;
}
