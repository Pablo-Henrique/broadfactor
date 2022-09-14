package com.broadfactor.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCrypt {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String passwordEncoder(String password) {
        return (password == null) ? null : PASSWORD_ENCODER.encode(password);
    }
}
