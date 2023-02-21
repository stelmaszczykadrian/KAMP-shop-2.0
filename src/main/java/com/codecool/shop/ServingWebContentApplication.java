package com.codecool.shop;

import com.codecool.shop.dao.implementation.JsonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ServingWebContentApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServingWebContentApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8888"));
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        //String sql = "";
        //jdbcTemplate.update(sql);

    }
}