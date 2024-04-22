package com.kulsin.service;

import com.kulsin.client.Post;

import java.util.List;

public interface AppService {

    List<Post> getPosts();

    Post getPostById(Long id);

}
