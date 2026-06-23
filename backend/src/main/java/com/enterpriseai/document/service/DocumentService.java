package com.enterpriseai.document.service;

import com.enterpriseai.document.dto.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

        DocumentResponse upload(MultipartFile file);

        List<DocumentResponse> list();
}
