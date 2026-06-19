package com.enterpriseai.conversation.entity;

import com.enterpriseai.common.entity.BaseEntity;
import com.enterpriseai.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}