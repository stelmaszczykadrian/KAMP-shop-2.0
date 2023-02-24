package com.codecool.shop.dao.jdbc;

import com.codecool.shop.model.user.User;
import com.codecool.shop.model.user.UserRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

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
    public void create(User user){
         System.out.println(user.getName());
        String sql = """
                INSERT INTO users (name, email, password, authority)
                VALUES (?,?,?,?);
                """;
        jdbcTemplate.update(sql, user.getName(),user.getEmail(),user.getPassword(),user.getAuthority());
    }

}
