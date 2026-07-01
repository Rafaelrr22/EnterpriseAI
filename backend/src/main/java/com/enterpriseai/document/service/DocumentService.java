package com.enterpriseai.document.service;

import com.enterpriseai.document.dto.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

        DocumentResponse upload(MultipartFile file);

        List<DocumentResponse> list();

        void delete(UUID id);
}
