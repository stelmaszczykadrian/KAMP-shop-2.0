package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller

public class ProductController {

    @GetMapping ( "/")
        public String index(Model model) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        model.addAttribute("products", productService.getAllProducts());

        return "index";

    }


    @GetMapping("/category/{id}")
        public String category(@PathVariable("id") int id, Model model){
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        model.addAttribute("category", productService.getProductCategory(id));
        model.addAttribute("products", productService.getProductsForCategory(id));

        return "category";
    }


    @GetMapping("/supplier/{id}")
    public String supplier(@PathVariable("id") int id, Model model){

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        model.addAttribute("products", productService.getSupplier(id).getProducts());

        return "supplier";
    }

}


