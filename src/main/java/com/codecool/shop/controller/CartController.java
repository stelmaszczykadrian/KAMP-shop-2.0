package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @RequestMapping(value="buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") int id,  HttpSession session){

        System.out.println("ok");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        if (session.getAttribute("cart") == null) {
            Cart cart = new Cart();
            cart.addProductToCart(productService.getProductById(id));
            session.setAttribute("cart", cart);
            System.out.println(cart.getCartItems());
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            cart.addProductToCart(productService.getProductById(id));
            session.setAttribute("cart", cart);
            System.out.println(cart.getCartItems());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, HttpSession session) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        Cart cart = (Cart) session.getAttribute("cart");
        cart.deleteProductFromCart(productService.getProductById(id));
        session.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

}
