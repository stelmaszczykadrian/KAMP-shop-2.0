package com.codecool.shop.controller;

import com.codecool.shop.dao.jdbc.UserDao;
import com.codecool.shop.user.User;
import com.codecool.shop.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@AllArgsConstructor
@Controller
public class RegisterController {
    private final JdbcTemplate jdbcTemplate;
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User emptyUser = new User(null,null,null,null);
        model.addAttribute("user", emptyUser);
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user){
        UserDao userDao = new UserDao(jdbcTemplate);
        userDao.create(user);
        return "/login";
    }
}

