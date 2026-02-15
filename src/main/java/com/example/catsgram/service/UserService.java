package com.example.catsgram.service;

import com.example.catsgram.exceptions.InvalidEmailException;
import com.example.catsgram.exceptions.UserAlreadyExistException;
import com.example.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
   private final Map<String, User> users = new HashMap<>();

    public Collection<User> getUsers() {
        return users.values();
    }

    public User createUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("Поле 'email' не должно быть пустым");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User updateUser(User user) {
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new InvalidEmailException("Email is empty");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail(String email) {
        if (email==null || email.isEmpty()) {
            return null;
        }
        return users.get(email);
    }

}
