package com.codecool.shop.model.user;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class UserService {
    public String passwordEncode(String password) {
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16384, 8, 4, 32, 64);
        var encodedPassword = encoder.encode(password);
        return encodedPassword;
    }

}
