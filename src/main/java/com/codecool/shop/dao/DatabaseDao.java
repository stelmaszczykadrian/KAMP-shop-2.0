package com.codecool.shop.dao;

import com.codecool.shop.user.User;
import com.codecool.shop.user.UserRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> find(String email){
        String sql = """
           SELECT id, name, email, password
           FROM users
           WHERE email = ?;
           """;
        return jdbcTemplate.query(sql,new UserRowMapper(), email)
                .stream()
                .findFirst();
    }
    public List<User> findAll(){
        String sql = """
           SELECT *
           FROM users
           """;
        return jdbcTemplate.query(sql,new UserRowMapper());
    }
}
