package com.broadfactor.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCryptUtils {

    private static final PasswordEncoder PASSWORD_ENCODER;

    static {
        PASSWORD_ENCODER = new BCryptPasswordEncoder();
    }

    /**
     * Privando objetos
     */
    private PasswordCryptUtils() {
    }

    /**
     * Criptografia para senha
     *
     * @param password Senha para ser encryptada.
     * @return Senha cryptografada
     */
    public static String passwordEncoder(String password) {
        return (password == null) ? null : PASSWORD_ENCODER.encode(password);
    }
}
