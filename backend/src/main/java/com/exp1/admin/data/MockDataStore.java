package com.exp1.admin.data;

import com.exp1.admin.model.Article;
import com.exp1.admin.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MockDataStore {


    private final List<Article> articles = new ArrayList<>(List.of(
            new Article(1, "Vue前端开发实战", "admin", "2025-01-01"),
            new Article(2, "ElementUI使用指南", "test", "2025-01-02"),
            new Article(3, "响应式布局设计", "editor", "2025-01-03"),
            new Article(4, "JavaScript基础语法", "writer", "2025-01-04"),
            new Article(5, "CSS高级技巧", "dev", "2025-01-05")
    ));

    private final Map<String, String> registeredUsers = new HashMap<>() {{
        put("admin", "123456");
    }};

    private final AtomicInteger userIdSequence = new AtomicInteger(6);
    private final AtomicInteger articleIdSequence = new AtomicInteger(6);


    public List<Article> getArticles() { return articles; }
    public Map<String, String> getRegisteredUsers() { return registeredUsers; }
    public int getNextUserId() { return userIdSequence.getAndIncrement(); }
    public int getNextArticleId() { return articleIdSequence.getAndIncrement(); }
}
