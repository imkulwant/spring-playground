package com.kulsin.service;

import com.kulsin.entity.Article;
import com.kulsin.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleResource {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/save")
    public Article saveArticle(@RequestBody Article article) {
        // TO-DO
        // return articleRepository.save(article);
        return null;
    }

}
