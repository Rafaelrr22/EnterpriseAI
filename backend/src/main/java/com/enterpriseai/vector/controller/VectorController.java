package com.enterpriseai.vector.controller;

import com.enterpriseai.common.security.AuthenticatedUserService;
import com.enterpriseai.user.entity.User;
import com.enterpriseai.vector.dto.SearchRequest;
import com.enterpriseai.vector.dto.SearchResult;
import com.enterpriseai.vector.service.VectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vector")
@RequiredArgsConstructor
public class VectorController {

    private final VectorService vectorService;
    private final AuthenticatedUserService authenticatedUserService;

    @PostMapping("/init")
    public String initialize() {

        vectorService.initializeCollection();

        return "Collection created.";
    }

    @PostMapping("/search")
    public List<SearchResult> search(
            @RequestBody SearchRequest request
    ) {

        User user = authenticatedUserService.getCurrentUser();

        return vectorService.search(
                request.getQuery(),
                user.getId()
        );

    }

}