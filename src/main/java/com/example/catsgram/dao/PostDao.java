package com.example.catsgram.dao;

import com.example.catsgram.model.Post;
import com.example.catsgram.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostDao {
    Collection<Post> findPostsByUser(User user);
    List<Post> findAll();
    Optional<Post> findById(Integer id);
    Post create(Post post);
}
