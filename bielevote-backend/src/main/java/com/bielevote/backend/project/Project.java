package com.bielevote.backend.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    public Project(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
