package com.enterpriseai.rag.service.impl;

import com.enterpriseai.ai.service.AiService;
import com.enterpriseai.rag.service.RagService;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RagServiceImpl implements RagService {

    private final VectorService vectorService;
    private final AiService aiService;

    @Override
    public String ask(String question) {

        List<String> chunks =
                vectorService.search(question);

        String context =
                String.join("\n\n", chunks);

        String prompt = """
                You are an AI assistant that answers questions ONLY using the provided context.

                If the answer cannot be found in the context, reply:
                "I could not find that information in the uploaded documents."

                Context:
                %s

                Question:
                %s

                Answer:
                """.formatted(context, question);

        return aiService.generateResponse(prompt);
    }
}