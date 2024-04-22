package com.kulsin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        value = "jplaceholder",
        url = "https://jsonplaceholder.typicode.com/",
        configuration = FeignClientConfig.class,
        fallback = JSONPlaceHolderFallback.class
)
public interface JSONPlaceHolderClient {

    @GetMapping(value = "/posts")
    List<Post> getPosts();

    @GetMapping(value = "/posts/{postId}")
    Post getPostById(@PathVariable("postId") Long postId);

}
