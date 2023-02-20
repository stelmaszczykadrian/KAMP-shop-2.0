package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService{
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public Supplier getSupplier(int supplierId) {
        return supplierDao.find(supplierId);
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }


    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public Product getProductById(int id){
        return productDao.find(id);
    }

}
