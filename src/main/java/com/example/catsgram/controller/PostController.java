package com.example.catsgram.controller;

import com.example.catsgram.exceptions.IncorrectParameterException;
import com.example.catsgram.exceptions.PostNotFoundException;
import com.example.catsgram.model.Post;
import com.example.catsgram.service.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Optional;

@RestController
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Collection<Post> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IncorrectParameterException("sort");
        }
        if (page < 0) {
            throw new IncorrectParameterException("page");
        }
        if (size <= 0) {
            throw new IncorrectParameterException("size");
        }

        Integer from = page * size;
        return postService.findAll(size, from, sort);
    }

    @GetMapping("/posts/{postId}")
    public Post findById(@PathVariable int postId) {
        return postService.findById(postId).orElseThrow(() -> new PostNotFoundException("Пост не найден"));
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

}
