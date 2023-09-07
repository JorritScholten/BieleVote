package com.bielevote.backend.news;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping
public class CategoryController {

    @GetMapping
    public Category[] getCategories() {
        return Category.values();
    }
}
