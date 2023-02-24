package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDao;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class OrderHistoryController {
    private final JdbcTemplate jdbcTemplate;

    @GetMapping( "/orders")
    public String orders(Model model, HttpSession session, Principal principal){
        OrderDao orderDao = new OrderDao(jdbcTemplate);
        List<Map<String, Object>> orders = orderDao.getAll(principal);
        model.addAttribute("orders", orders);
        return "orders";
    }
}
