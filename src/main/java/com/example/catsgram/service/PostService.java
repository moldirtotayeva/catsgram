package com.example.catsgram.service;

import com.example.catsgram.dao.PostDao;
import com.example.catsgram.exceptions.PostNotFoundException;
import com.example.catsgram.exceptions.UserNotFoundException;
import com.example.catsgram.model.Post;
import com.example.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;
    private final UserService userService;


    public Collection<Post> findAllPosts() {
        return postDao.findAll();
    }

    public Post createPost(Post post) {
        return postDao.create(post);
    }

    public Post findPostById(int postId) {
        return postDao.findById(postId).orElseThrow(() -> new PostNotFoundException("Пост не найден"));
    }

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден."));
        return postDao.findPostsByUser(user);
    }
}
