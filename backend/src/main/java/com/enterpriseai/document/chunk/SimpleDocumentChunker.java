package com.enterpriseai.document.chunk;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleDocumentChunker implements DocumentChunker {

    private static final int CHUNK_SIZE = 500;

    @Override
    public List<String> chunk(String text) {

        List<String> chunks = new ArrayList<>();

        for (int i = 0; i < text.length(); i += CHUNK_SIZE) {

            int end = Math.min(i + CHUNK_SIZE, text.length());

            chunks.add(text.substring(i, end));
        }

        return chunks;
    }
}