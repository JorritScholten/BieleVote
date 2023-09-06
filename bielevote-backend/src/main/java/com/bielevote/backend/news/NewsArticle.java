package com.bielevote.backend.news;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
//    private Category category;
//    private Reaction reaction;
}
