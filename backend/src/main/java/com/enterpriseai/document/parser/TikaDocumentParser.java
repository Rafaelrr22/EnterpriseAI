package com.enterpriseai.document.parser;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TikaDocumentParser implements DocumentParser {

    private final Tika tika = new Tika();

    @Override
    public String parse(File file) {

        try {
            return tika.parseToString(file);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse document.", e);
        }
    }
}