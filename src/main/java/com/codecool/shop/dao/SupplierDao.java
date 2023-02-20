package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(int id);
    void remove(int id);

    List<Supplier> getAll();
}
