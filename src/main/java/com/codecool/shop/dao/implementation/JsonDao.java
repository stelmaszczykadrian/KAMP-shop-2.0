package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonDao implements com.codecool.shop.dao.JsonDao {
// TODO change filename to OrderID and Date
    private final String path = Paths.get("").toAbsolutePath().toString()+ "/src/main/resources/carts/carts.json";

    private void tryToSaveFile(JSONObject jsonObject) {
        try {
            FileWriter file = new FileWriter(path);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(Map<String, Integer> cart) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", 1);

        cart.forEach((product, value) -> {
            jsonObject.put(product, value);
        });
        tryToSaveFile(jsonObject);
        System.out.println("JSON file created: " + jsonObject);
    }

    public JSONObject readJson() {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(path)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            return jsonObject;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

