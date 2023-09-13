package com.bielevote.backend.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/v1/articles")
public class NewsArticleController {

    @Autowired
    NewsArticleRepository newsArticleRepository;

//    @GetMapping
//    public List<NewsArticle> getAllArticles() {
//        return newsArticleRepository.findAll();
//    }

    @GetMapping("/{id}")
    public Optional<NewsArticle> getArticleById(@PathVariable("id") long id) {
        return newsArticleRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

    record NewsArticlePreviewDto(long id, String title, LocalDateTime datePlaced, String summaryPreview) {
        public static NewsArticlePreviewDto from(NewsArticle newsArticle) {
            String[] previewWords = newsArticle.getSummary().split(" ");
            var preview = String.join(" ", Arrays.copyOf(previewWords, 20)) + "...";
            return new NewsArticlePreviewDto(newsArticle.getId(), newsArticle.getTitle(), newsArticle.getDatePlaced(), preview);
        }
    }

    @GetMapping
    public List<NewsArticlePreviewDto> getArticlePreviews() {
        List<NewsArticle> newsArticles = newsArticleRepository.findAll();
        return newsArticles.stream()
                .map(NewsArticlePreviewDto::from)
                .toList();
    }

    @GetMapping("/pages")
    public ResponseEntity<Map<String, Object>> getAllArticles(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<NewsArticle> newsArticles;
            PageRequest paging = PageRequest.of(page, size, Sort.by("datePlaced").descending());

            Page<NewsArticle> pageNewsPre = newsArticleRepository.findAll(paging);

            newsArticles = pageNewsPre.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("articles", new ArrayList<>((newsArticles).stream()
                    .map(NewsArticlePreviewDto::from)
                    .toList()));
            response.put("currentPage", pageNewsPre.getNumber());
            response.put("totalItems", pageNewsPre.getTotalElements());
            response.put("totalPages", pageNewsPre.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
