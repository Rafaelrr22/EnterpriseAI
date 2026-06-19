package com.enterpriseai.conversation.service;

import com.enterpriseai.conversation.dto.ConversationResponse;
import com.enterpriseai.conversation.dto.CreateConversationRequest;

import java.util.List;

public interface ConversationService {

    ConversationResponse createConversation(CreateConversationRequest request);

    List<ConversationResponse> getMyConversations();

}