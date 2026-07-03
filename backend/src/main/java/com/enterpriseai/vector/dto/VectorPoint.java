package com.enterpriseai.vector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class VectorPoint {

    private String id;
    private List<Double> vector;
    private String content;
    private String documentId;
    private String userId;

}