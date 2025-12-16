package com.minigame.controller;

import com.minigame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> res = new HashMap<>();
        if (userService.register(username, password)) {
            res.put("code", 200);
            res.put("msg", "注册成功");
        } else {
            res.put("code", 500);
            res.put("msg", "用户名已存在");
        }
        return res;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> res = new HashMap<>();
        if (userService.login(username, password)) {
            res.put("code", 200);
            res.put("msg", "登录成功");
            res.put("username", username);
        } else {
            res.put("code", 500);
            res.put("msg", "账号或密码错误");
        }
        return res;
    }
}