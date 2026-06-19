package com.enterpriseai.conversation.service.impl;

import com.enterpriseai.conversation.dto.ConversationResponse;
import com.enterpriseai.conversation.dto.CreateConversationRequest;
import com.enterpriseai.conversation.entity.Conversation;
import com.enterpriseai.conversation.repository.ConversationRepository;
import com.enterpriseai.conversation.service.ConversationService;
import com.enterpriseai.user.entity.User;
import com.enterpriseai.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Override
    public ConversationResponse createConversation(CreateConversationRequest request) {

        User user = getAuthenticatedUser();

        Conversation conversation = Conversation.builder()
                .title(request.getTitle())
                .user(user)
                .build();

        Conversation savedConversation = conversationRepository.save(conversation);

        return toResponse(savedConversation);
    }

    @Override
    public List<ConversationResponse> getMyConversations() {

        User user = getAuthenticatedUser();

        return conversationRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private User getAuthenticatedUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalStateException("Authenticated user not found."));
    }

    private ConversationResponse toResponse(Conversation conversation) {

        return new ConversationResponse(
                conversation.getId(),
                conversation.getTitle()
        );
    }
}