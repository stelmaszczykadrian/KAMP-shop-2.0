package com.codecool.shop.controller;

import com.codecool.shop.dao.jdbc.UserDao;
import com.codecool.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private final JdbcTemplate jdbcTemplate;

    @GetMapping ( "/")
        public String index(Model model) {
        UserDao databaseDao = new UserDao(jdbcTemplate);
        databaseDao.find("a");
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }
    @GetMapping("/category/{id}")
        public String category(@PathVariable("id") int id, Model model){
        model.addAttribute("category", productService.getProductCategory(id));
        model.addAttribute("products", productService.getProductsForCategory(id));
        return "category";
    }


    @GetMapping("/supplier/{id}")
    public String supplier(@PathVariable("id") int id, Model model){
        model.addAttribute("supplier", productService.getSupplier(id));
        return "supplier";
    }

}


