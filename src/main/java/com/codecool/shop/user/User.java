package com.codecool.shop.user;

public class User {
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    private String password;
    private String name;
    private String email;
    private Integer id;

    public User(Integer id, String name, String email, String password) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.id = id;

    }

    public void isUserExist(String name, String password){

    }
}
