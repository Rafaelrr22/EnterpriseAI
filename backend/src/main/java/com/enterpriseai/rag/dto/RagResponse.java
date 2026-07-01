package com.enterpriseai.rag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RagResponse {

    private String answer;

    private List<String> sources;

}