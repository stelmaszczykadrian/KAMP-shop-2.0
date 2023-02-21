package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    void remove(int id);
    List<ProductCategory> getAll();

}
