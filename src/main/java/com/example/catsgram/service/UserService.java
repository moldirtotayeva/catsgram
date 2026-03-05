package com.example.catsgram.service;

import com.example.catsgram.dao.UserDao;
import com.example.catsgram.exceptions.InvalidEmailException;
import com.example.catsgram.exceptions.UserAlreadyExistException;
import com.example.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserDao userDao;

  public Optional<User> findUserById(String id){
      return userDao.findById(id);
  }

  public List<User> findAllUsers(){
      return userDao.findAll();
  }

  public User creatUser(User user){
      return userDao.create(user);
  }
}
