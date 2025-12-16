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

    // 注册接口（保持不变，适配URL参数）
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> res = new HashMap<>();
        try {
            if (userService.register(username, password)) {
                res.put("code", 200);
                res.put("msg", "注册成功");
            } else {
                res.put("code", 500);
                res.put("msg", "用户名已存在");
            }
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "注册失败：" + e.getMessage());
        }
        return res;
    }

    // 登录接口（优化参数缺失提示）
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        Map<String, Object> res = new HashMap<>();
        try {
            // 新增参数校验：避免参数缺失导致的空指针
            if (username == null || password == null) {
                res.put("code", 400);
                res.put("msg", "参数缺失：用户名或密码不能为空");
                return res;
            }
            if (userService.login(username, password)) {
                res.put("code", 200);
                res.put("msg", "登录成功");
                res.put("username", username);
            } else {
                res.put("code", 500);
                res.put("msg", "账号或密码错误");
            }
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "登录失败：" + e.getMessage());
        }
        return res;
    }
}