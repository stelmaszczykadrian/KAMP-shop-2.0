package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @GetMapping( "/payment")
    public String payment(Model model, HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "payment";
    }

}
