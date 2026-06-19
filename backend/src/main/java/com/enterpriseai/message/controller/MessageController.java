package com.enterpriseai.message.controller;

import com.enterpriseai.message.dto.MessageResponse;
import com.enterpriseai.message.dto.SendMessageRequest;
import com.enterpriseai.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations/{conversationId}/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public MessageResponse sendMessage(
            @PathVariable UUID conversationId,
            @Valid @RequestBody SendMessageRequest request
    ) {
        return messageService.sendMessage(
                conversationId,
                request
        );
    }

    @GetMapping
    public List<MessageResponse> getMessages(
            @PathVariable UUID conversationId
    ) {
        return messageService.getConversationMessages(
                conversationId
        );
    }

}