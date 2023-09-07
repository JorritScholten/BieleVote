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

    public NewsArticle(String title, String summary, List<String> content, String author, Date date, Category category) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
    }
}
