package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Initializer {
    @Autowired
    public void contextInitialized() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "Television");
        supplierDataStore.add(samsung);
        Supplier apple = new Supplier("Apple", "Digital content and services");
        supplierDataStore.add(apple);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Media", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        ProductCategory television = new ProductCategory("Television", "Media", "A system for converting visual images (with sound) into electrical signals, transmitting them by radio or other means, and displaying them electronically on a screen.");
        productCategoryDataStore.add(television);

        ProductCategory mobilePhone = new ProductCategory("Mobile Phone", "Media", "Portable device for connecting to a telecommunications network in order to transmit and receive voice, video, or other data.");
        productCategoryDataStore.add(mobilePhone);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("SAMSUNG QE43Q67B", new BigDecimal("2000"), "USD", "Television", television, samsung));
        productDataStore.add(new Product("APPLE iPhone 14 Pro", new BigDecimal("3000"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", mobilePhone, apple));


    }
}
