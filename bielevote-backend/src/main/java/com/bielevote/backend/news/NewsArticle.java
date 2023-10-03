package com.bielevote.backend.news;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Jacksonized
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_ARTICLE_ID_SEQ")
    @SequenceGenerator(name = "NEWS_ARTICLE_ID_SEQ", sequenceName = "NEWS_ARTICLE_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    private String title;

    @Column(columnDefinition = "CLOB")
    private String summary;

    @Column(columnDefinition = "CLOB")
    private String content;

    private String author;

    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime datePlaced;

    @Enumerated(value = EnumType.STRING)
    private Category category;
}
