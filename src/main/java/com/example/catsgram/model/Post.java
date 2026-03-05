package com.example.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private Integer id;
    private final User author; // автор
    private final LocalDate creationDate; // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(User author, LocalDate creationDate, String description, String photoUrl) {
        this.author = author;
        this.creationDate = creationDate;
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
