package com.enterpriseai.message.service;

import com.enterpriseai.message.dto.MessageResponse;
import com.enterpriseai.message.dto.SendMessageRequest;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    MessageResponse sendMessage(
            UUID conversationId,
            SendMessageRequest request
    );

    List<MessageResponse> getConversationMessages(
            UUID conversationId
    );

}