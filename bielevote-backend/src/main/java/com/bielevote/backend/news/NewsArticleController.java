package com.bielevote.backend.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/articles")
public class NewsArticleController {

    @Autowired
    NewsArticleRepository newsArticleRepository;
    

    @GetMapping
    public List<NewsArticle> getAllArticles() {
        return newsArticleRepository.findAll();
    }

    @PostMapping
    public NewsArticle postArticle(@RequestBody NewsArticle newsArticle) {
        return newsArticleRepository.save(newsArticle);
    }
}
