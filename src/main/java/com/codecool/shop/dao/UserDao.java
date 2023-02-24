package com.codecool.shop.dao;

import com.codecool.shop.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void create(User user);
}
