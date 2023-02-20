package com.codecool.shop.controller;

import com.codecool.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping ( "/")
        public String index(Model model) {
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


