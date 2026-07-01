package com.enterpriseai.vector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResult {

    private String content;

    private String documentId;

}