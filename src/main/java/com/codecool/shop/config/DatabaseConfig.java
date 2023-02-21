package com.codecool.shop.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


public class DatabaseConfig {

    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/kamp_shop");
        dataSourceBuilder.username("papryk");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }

}
