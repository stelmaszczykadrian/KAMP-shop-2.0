package com.codecool.shop.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
@Controller
@AllArgsConstructor
public class DatabaseDao {
    private final JdbcTemplate jdbcTemplate;
    public void insertSupplier(String name, String description){

        var sql = """
                INSERT INTO supplier (name, description)
                VALUES (?, ?);
                 """;
        jdbcTemplate.update(sql, name, description);
    }
}
