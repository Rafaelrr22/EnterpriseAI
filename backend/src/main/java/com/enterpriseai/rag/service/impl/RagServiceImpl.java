package com.enterpriseai.rag.service.impl;

import com.enterpriseai.ai.service.AiService;
import com.enterpriseai.rag.service.RagService;
import com.enterpriseai.vector.dto.SearchResult;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RagServiceImpl implements RagService {

    private final VectorService vectorService;
    private final AiService aiService;

    @Override
    public String ask(String question) {

        List<SearchResult> chunks =
                vectorService.search(question);

        String context = chunks.stream()
                .map(SearchResult::getContent)
                .collect(Collectors.joining("\n\n"));

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