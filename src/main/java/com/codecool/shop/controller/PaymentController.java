package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDao;
import com.codecool.shop.model.Cart;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class PaymentController {
    private final JdbcTemplate jdbcTemplate;

    @GetMapping( "/payment")
    public String payment(Model model, HttpSession session, Principal principal){
        OrderDao orderDao = new OrderDao(jdbcTemplate);
        Cart cart = (Cart) session.getAttribute("cart");

        orderDao.save(cart, principal);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "payment";
    }

    @PostMapping("/payment")
    public String pay(Principal principal){
        OrderDao orderDao = new OrderDao(jdbcTemplate);
        orderDao.pay(principal);
        return "/";
    }
}
