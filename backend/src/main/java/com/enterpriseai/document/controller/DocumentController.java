package com.enterpriseai.document.controller;

import com.enterpriseai.document.dto.DocumentResponse;
import com.enterpriseai.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentResponse upload(
            @RequestParam("file") MultipartFile file
    ) {
        return documentService.upload(file);
    }

    @GetMapping
    public List<DocumentResponse> list() {
        return documentService.list();
    }

}