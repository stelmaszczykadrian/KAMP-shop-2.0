package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "cart")
public class CartController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "cart/index";
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") int id, HttpSession session) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);

        if (session.getAttribute("cart") == null) {
            Cart cart = new Cart();
            cart.addProductToCart(productService.getProductById(id));
            session.setAttribute("cart", cart);
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            cart.addProductToCart(productService.getProductById(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/index";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, HttpSession session) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);

        Cart cart = (Cart) session.getAttribute("cart");
        cart.deleteProductFromCart(productService.getProductById(id));
        session.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

}
