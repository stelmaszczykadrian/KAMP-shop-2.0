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

    public String getAuthority() {
        return authority;
    }

    private String authority = "ROLE_USER";
    private UserService userService = new UserService();

    public User(Integer id, String name, String email, String password) {
        if (password != null){
            this.password = userService.passwordEncode(password);
        }
        this.name = name;
        this.email = email;
        this.id = id;

    }
}
