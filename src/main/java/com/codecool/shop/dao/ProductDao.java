package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);
    List<Product> getAll();
    List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);

}
