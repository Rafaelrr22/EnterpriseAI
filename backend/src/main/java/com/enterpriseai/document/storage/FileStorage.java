package com.enterpriseai.document.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {

    String store(MultipartFile file) throws IOException;

}