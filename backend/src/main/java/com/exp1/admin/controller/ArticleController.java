package com.exp1.admin.controller;

import com.exp1.admin.model.ApiResponse;
import com.exp1.admin.model.Article;
import com.exp1.admin.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ApiResponse getArticles(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String title) {
        List<Article> all = articleService.getAllArticles();

        // 按标题过滤
        if (title != null && !title.trim().isEmpty()) {
            all = all.stream()
                    .filter(a -> a.getTitle() != null && a.getTitle().contains(title.trim()))
                    .collect(Collectors.toList());
        }

        int total = all.size();
        int from = (page - 1) * pageSize;
        int to = Math.min(from + pageSize, total);
        List<Article> list = from < total ? all.subList(from, to) : List.of();

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return ApiResponse.ok("获取成功", result);
    }
}
