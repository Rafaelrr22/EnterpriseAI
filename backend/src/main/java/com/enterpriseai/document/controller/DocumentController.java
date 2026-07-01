package com.enterpriseai.document.controller;

import com.enterpriseai.document.dto.DocumentResponse;
import com.enterpriseai.document.entity.Document;
import com.enterpriseai.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        documentService.delete(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(
            @PathVariable UUID id
    ) {

        Document document =
                documentService.download(id);

        Resource resource =
                new FileSystemResource(document.getStorePath());

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                document.getFilename() +
                                "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }

}