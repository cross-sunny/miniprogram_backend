package com.minigame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 CSRF（小程序调用不需要）
                .csrf(csrf -> csrf.disable())
                // 配置接口访问权限
                .authorizeHttpRequests(auth -> auth
                        // 放行所有 /api/** 路径（你的注册/登录接口）
                        .requestMatchers("/api/**").permitAll()
                        // 其他路径仍需验证（可选，开发环境可直接放行所有）
                        .anyRequest().permitAll()
                )
                // 关闭默认的登录页面拦截
                .formLogin(form -> form.disable())
                // 关闭默认的退出功能
                .logout(logout -> logout.disable());

        return http.build();
    }
}