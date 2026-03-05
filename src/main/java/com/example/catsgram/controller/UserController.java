package com.example.catsgram.controller;

import com.example.catsgram.model.User;
import com.example.catsgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{login}")
    public Optional<User> getUser(@PathVariable String login) {
        return userService.findUserById(login);
    }

    @PostMapping
    public User create(@RequestBody User user) {
       return userService.creatUser(user);
    }

//    @PutMapping
//    public User update(@RequestBody User user) {
//        return userService.updateUser(user);
//    }

}
