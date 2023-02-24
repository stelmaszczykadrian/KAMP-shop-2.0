package com.codecool.shop.dao.jdbc;

import com.codecool.shop.model.Cart;
import com.codecool.shop.user.User;
import com.codecool.shop.user.UserRowMapper;
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

    public List<Map<String, Object>> get_all_orders(Principal principal){
        return jdbcTemplate.queryForList("""
                SELECT * FROM orders
                WHERE email = ?
                """, principal.getName());
    }
    public List<User> findAll(){
        String sql = """
           SELECT *
           FROM users
           """;
        return jdbcTemplate.query(sql,new UserRowMapper());
    }
}
