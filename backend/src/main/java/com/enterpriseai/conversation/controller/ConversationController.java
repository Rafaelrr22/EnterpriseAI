package com.enterpriseai.conversation.controller;

import com.enterpriseai.conversation.dto.ConversationResponse;
import com.enterpriseai.conversation.dto.CreateConversationRequest;
import com.enterpriseai.conversation.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    public ConversationResponse createConversation(
            @Valid @RequestBody CreateConversationRequest request
    ) {
        return conversationService.createConversation(request);
    }

    @GetMapping
    public List<ConversationResponse> getMyConversations() {
        return conversationService.getMyConversations();
    }
}