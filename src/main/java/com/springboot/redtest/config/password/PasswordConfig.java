package com.springboot.redtest.config.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig {

    public PasswordEncoder bPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
