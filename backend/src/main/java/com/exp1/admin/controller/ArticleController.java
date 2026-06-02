package com.exp1.admin.controller;

import com.exp1.admin.model.ApiResponse;
import com.exp1.admin.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ApiResponse getArticles() {
        return ApiResponse.ok("获取成功", articleService.getAllArticles());
    }
}
