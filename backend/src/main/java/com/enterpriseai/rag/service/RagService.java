package com.enterpriseai.rag.service;

import com.enterpriseai.rag.dto.RagResponse;

public interface RagService {

    RagResponse ask(String question);

}