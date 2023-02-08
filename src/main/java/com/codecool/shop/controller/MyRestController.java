package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.JsonDao;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class MyRestController {

        @GetMapping(path = "/carts")
        public JSONObject sayHello()
        {

            return new JsonDao().readJson();
        }
    }

