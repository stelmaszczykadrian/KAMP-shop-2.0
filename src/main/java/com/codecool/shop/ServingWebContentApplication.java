package com.codecool.shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ServingWebContentApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServingWebContentApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8888"));
        app.run(args);
    }
}
