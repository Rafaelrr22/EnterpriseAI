package com.enterpriseai.conversation.service.impl;

import com.enterpriseai.common.exception.ResourceNotFoundException;
import com.enterpriseai.common.security.AuthenticatedUserService;
import com.enterpriseai.conversation.dto.ConversationResponse;
import com.enterpriseai.conversation.dto.CreateConversationRequest;
import com.enterpriseai.conversation.entity.Conversation;
import com.enterpriseai.conversation.repository.ConversationRepository;
import com.enterpriseai.conversation.service.ConversationService;
import com.enterpriseai.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @Override
    public ConversationResponse createConversation(CreateConversationRequest request) {

        User user = authenticatedUserService.getCurrentUser();

        Conversation conversation = Conversation.builder()
                .title(request.getTitle())
                .user(user)
                .build();

        Conversation savedConversation = conversationRepository.save(conversation);


        return toResponse(savedConversation);
    }

    @Override
    public List<ConversationResponse> getMyConversations() {

        User user = authenticatedUserService.getCurrentUser();

        return conversationRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }



    private ConversationResponse toResponse(Conversation conversation) {

        return new ConversationResponse(
                conversation.getId(),
                conversation.getTitle()
        );
    }
}