package com.example.catsgram.service;

import com.example.catsgram.exceptions.UserNotFoundException;
import com.example.catsgram.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final Map<Integer, Post> posts = new HashMap<>();
    private int uniqueId = 1;
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public Collection<Post> findAll(Integer size, Integer from, String sort) {
        return posts.values().stream().sorted(
                        sort.equals("desc")
                                ? Comparator.comparing(Post::getCreationDate).reversed()
                                : Comparator.comparing(Post::getCreationDate))
                .skip(from)
                .limit(size)
                .toList();
    }

    public Post create(Post post) {
        post.setId(uniqueId++);
        if(userService.findUserByEmail(post.getAuthor())==null){
            throw new UserNotFoundException(String.format("Пользователь %s не найден", post.getAuthor()));
        }
        posts.put(post.getId(), post);
        return post;
    }

    public Optional<Post> findById(int postId) {
        return posts.values().stream().filter(x->x.getId()==postId).findFirst();
    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.values().stream()
                .filter(post -> email.equals(post.getAuthor()))
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate()); // прямой порядок сортировки
                    if ("desc".equals(sort)) { // обратный порядок сортировки
                        comparison *= -1;
                    }
                    return comparison;
                })
                .limit(size)
                .collect(Collectors.toList());
    }
}
