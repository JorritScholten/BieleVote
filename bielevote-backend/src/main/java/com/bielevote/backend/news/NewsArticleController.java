package com.bielevote.backend.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/news")
public class NewsArticleController {

    @Autowired
    NewsArticleRepository newsArticleRepository;

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticle> getArticleById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(newsArticleRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<NewsArticle> postArticle(@RequestBody NewsArticle newsArticle) {
        try {
            if (newsArticle.getDatePlaced() == null) {
                newsArticle.setDatePlaced(LocalDateTime.now());
            }
            return new ResponseEntity<>(newsArticleRepository.save(newsArticle), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable("id") long id) {
        try {
            newsArticleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<NewsArticle> newsArticles;
            PageRequest paging = PageRequest.of(page, size, Sort.by("datePlaced").descending());

            Page<NewsArticle> pageNewsPre = newsArticleRepository.findAll(paging);

            newsArticles = pageNewsPre.getContent();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("articles", new ArrayList<>((newsArticles).stream()
                    .map(NewsArticlePreviewDto::from)
                    .toList()));
            responseBody.put("currentPage", pageNewsPre.getNumber());
            responseBody.put("totalItems", pageNewsPre.getTotalElements());
            responseBody.put("totalPages", pageNewsPre.getTotalPages());

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    record NewsArticlePreviewDto(long id, String title, LocalDateTime datePlaced, String summaryPreview) {
        public static NewsArticlePreviewDto from(NewsArticle newsArticle) {
            String[] previewWords = newsArticle.getSummary().split(" ");
            var preview = String.join(" ", Arrays.copyOf(previewWords, 20)) + "...";
            return new NewsArticlePreviewDto(newsArticle.getId(), newsArticle.getTitle(), newsArticle.getDatePlaced(), preview);
        }
    }
}
