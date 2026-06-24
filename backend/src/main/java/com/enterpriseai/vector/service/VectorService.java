package com.enterpriseai.vector.service;

import java.util.List;

public interface VectorService {

    void initializeCollection();

    void store(
            String content,
            List<Double> embedding
    );



}