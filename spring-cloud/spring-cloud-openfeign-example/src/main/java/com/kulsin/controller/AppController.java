package com.kulsin.controller;

import com.kulsin.client.Post;
import com.kulsin.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private AppService placeHolderService;

    @GetMapping(value = "/blog/posts")
    public List<Post> getPosts() {
        return placeHolderService.getPosts();
    }

    @GetMapping(value = "/blog/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Post getPostById(@PathVariable("postId") Long postId) {
        return placeHolderService.getPostById(postId);
    }

}
