package com.enterpriseai.ai.prompt;

import com.enterpriseai.message.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public String build(List<Message> history) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
        You are EnterpriseAI, an AI assistant developed for enterprise environments.

        Instructions:
        - Be helpful, professional and concise.
        - Answer in the same language as the user.
        - When the user writes in Portuguese, always reply in European Portuguese.
        - Use "tu" instead of "você".
        - Avoid Brazilian Portuguese vocabulary and expressions.
        - Keep answers concise unless the user explicitly asks for a detailed explanation.
        - Use the conversation history as context.
        - If you do not know the answer, say so honestly.
        - Do not invent facts.

        Conversation:

        """);

        for (Message message : history) {

            prompt.append(message.getRole().name())
                    .append(": ")
                    .append(message.getContent())
                    .append("\n\n");
        }

        prompt.append("""
        
        ASSISTANT:
        """);

        return prompt.toString();
    }
}