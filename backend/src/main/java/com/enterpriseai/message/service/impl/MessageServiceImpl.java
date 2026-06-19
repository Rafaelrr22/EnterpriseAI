package com.enterpriseai.message.service.impl;

import com.enterpriseai.common.exception.ResourceNotFoundException;
import com.enterpriseai.common.security.AuthenticatedUserService;
import com.enterpriseai.conversation.entity.Conversation;
import com.enterpriseai.conversation.repository.ConversationRepository;
import com.enterpriseai.message.dto.MessageResponse;
import com.enterpriseai.message.dto.SendMessageRequest;
import com.enterpriseai.message.entity.Message;
import com.enterpriseai.message.entity.MessageRole;
import com.enterpriseai.message.repository.MessageRepository;
import com.enterpriseai.message.service.MessageService;
import com.enterpriseai.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @Override
    public MessageResponse sendMessage(
            UUID conversationId,
            SendMessageRequest request
    ) {

        User user = authenticatedUserService.getCurrentUser();

        Conversation conversation = conversationRepository
                .findByIdAndUser(conversationId, user)
                .orElseThrow(() ->
                     new ResourceNotFoundException("Conversation not found.")
                );

        Message message = Message.builder()
                .conversation(conversation)
                .role(MessageRole.USER)
                .content(request.getContent())
                .build();

        Message savedMessage = messageRepository.save(message);

        return toResponse(savedMessage);
    }

    @Override
    public List<MessageResponse> getConversationMessages(UUID conversationId) {

        User user = authenticatedUserService.getCurrentUser();

        Conversation conversation = conversationRepository
                .findByIdAndUser(conversationId, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conversation not found.")
                );

        return messageRepository
                .findByConversationOrderByCreatedAtAsc(conversation)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private MessageResponse toResponse(Message message) {

        return new MessageResponse(
                message.getId(),
                message.getRole().name(),
                message.getContent()
        );
    }

}