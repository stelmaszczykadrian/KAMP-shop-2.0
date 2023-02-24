package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Map<String, Object>> getAll(Principal principal);
    void save(Cart cart, Principal principal);
    void pay(Principal principal);

}
