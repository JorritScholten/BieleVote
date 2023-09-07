package com.bielevote.backend.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NewsArticle {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String summary;
    private List<String> content;
    private String author;
    private Date date;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @Enumerated(value = EnumType.STRING)
    private Reaction reaction;

    public NewsArticle(String title, String summary, Category category) {
        this.title = title;
        this.summary = summary;
        this.category = category;
    }
}
