package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

@Controller
public class CartController {


    @PostMapping("/addToCart")
    public String addToCart(HttpSession session, Model model, @RequestParam("id") int id, @RequestParam("quantity") int quantity) {

        //sessionToken
        String sessionToken = (String) session.getAttribute("sessionToken");
        if(sessionToken == null) {

            // UUID -
            session.setAttribute("sessionToken", UUID.randomUUID().toString());
            Cart cart = new Cart();
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(cartItem.getProductById(id)); //get product by id
            cart.getCartItems().add(cartItem);

        }

        return "redirect:/";
    }

}
