package com.enterpriseai.document.repository;

import com.enterpriseai.document.entity.Document;
import com.enterpriseai.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    List<Document> findByUploadedBy(User user);
}
