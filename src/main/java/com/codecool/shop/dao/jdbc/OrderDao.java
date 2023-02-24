package com.codecool.shop.dao.jdbc;

import com.codecool.shop.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class OrderDao {
    private final JdbcTemplate jdbcTemplate;
    public void save(Cart cart, Principal principal){
        jdbcTemplate.update("""
            INSERT INTO orders (total_price, email, is_paid)
            VALUES (?,?,?)
            """, cart.getTotalPrice(), principal.getName(), false);
    }

    public List<Map<String, Object>> getAll(Principal principal){
        return jdbcTemplate.queryForList("""
                    SELECT * FROM orders
                    WHERE email = ?
                """, principal.getName());
    }

    public void pay(Principal principal) {
        jdbcTemplate.update("""
            UPDATE orders
            SET is_paid = true
            WHERE email = ?
        """, principal.getName());
    }
}
