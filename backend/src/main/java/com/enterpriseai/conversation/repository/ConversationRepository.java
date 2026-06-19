package com.enterpriseai.conversation.repository;

import com.enterpriseai.conversation.entity.Conversation;
import com.enterpriseai.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    List<Conversation> findByUser(User user);

    Optional<Conversation> findByIdAndUser(UUID id, User user);

}