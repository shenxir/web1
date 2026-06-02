package com.exp1.admin.service;

import com.exp1.admin.data.MockDataStore;
import com.exp1.admin.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final MockDataStore dataStore;

    public ArticleService(MockDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Article> getAllArticles() {
        return dataStore.getArticles();
    }
}
