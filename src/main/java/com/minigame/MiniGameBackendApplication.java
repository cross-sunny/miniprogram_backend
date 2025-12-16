package com.minigame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MiniGameBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniGameBackendApplication.class, args);
    }

    // 提供 BCrypt 密码加密工具的 Bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}