package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.Map;

public interface JsonDao {

    void save(Map<String, Integer> cart);
}
