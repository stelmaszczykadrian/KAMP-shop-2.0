package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.ProductService;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ProductController {
    @GetMapping( "/")
    public String index() {
        return "index";
    }
//    Map<String, Object> params = new HashMap<>();

//     params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
//
//        context.setVariable("category", productService.getProductCategory(1));
//        context.setVariable("products", productService.getProductsForCategory(1));
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
//    @GetMapping("/")
//    public ProductController greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new ProductController(counter.incrementAndGet(), String.format(template, name));
//    }
    }


