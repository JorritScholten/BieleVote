package com.bielevote.backend.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<NewsArticle> getArticleById(@PathVariable("id") long id) {
        return newsArticleRepository.findById(id);
    }

    @PostMapping
    public NewsArticle postArticle(@RequestBody NewsArticle newsArticle) {
        if (newsArticle.getDatePlaced() == null) {
            newsArticle.setDatePlaced(LocalDateTime.now());
        }
        return newsArticleRepository.save(newsArticle);
    }

    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable("id") long id) {
        newsArticleRepository.deleteById(id);
    }
}
