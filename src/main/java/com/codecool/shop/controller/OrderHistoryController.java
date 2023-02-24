package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
@AllArgsConstructor
@Controller
public class OrderHistoryController {
    private final JdbcTemplate jdbcTemplate;

    @GetMapping( "/orders")
    public String orders(Model model, HttpSession session, Principal principal){
        Cart cart = (Cart) session.getAttribute("cart");

        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "orders";
    }
}
