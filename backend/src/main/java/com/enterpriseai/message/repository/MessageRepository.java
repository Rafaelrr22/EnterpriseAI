package com.enterpriseai.message.repository;

import com.enterpriseai.conversation.entity.Conversation;
import com.enterpriseai.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByConversationOrderByCreatedAtAsc(Conversation conversation);

}