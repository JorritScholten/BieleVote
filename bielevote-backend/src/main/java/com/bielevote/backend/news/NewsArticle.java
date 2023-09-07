package com.bielevote.backend.news;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @GeneratedValue
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
