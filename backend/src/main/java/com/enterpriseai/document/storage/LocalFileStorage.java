package com.enterpriseai.document.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class LocalFileStorage implements FileStorage {

    private static final Path UPLOAD_DIR = Paths.get("uploads");

    @Override
    public String store(MultipartFile file) throws IOException {

        Files.createDirectories(UPLOAD_DIR);

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path destination = UPLOAD_DIR.resolve(filename);

        file.transferTo(destination);

        return destination.toString();
    }

}