package com.enterpriseai.document.entity;

import com.enterpriseai.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String storePath;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private Instant uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User uploadedBy;

}