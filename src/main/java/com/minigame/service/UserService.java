package com.minigame.service;

import com.minigame.entity.User;
import com.minigame.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    // 注入 UserMapper（操作数据库）
    @Autowired
    private UserMapper userMapper;

    // 注入密码加密工具
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 注册功能
    public boolean register(String username, String password) {
        // 1. 检查用户名是否已存在
        User existingUser = userMapper.selectByUsername(username);
        if (existingUser != null) {
            return false; // 用户名已存在
        }
        // 2. 密码加密
        String encryptedPwd = passwordEncoder.encode(password);
        // 3. 新增用户到数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptedPwd);
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return true;
    }

    // 登录功能
    public boolean login(String username, String password) {
        // 1. 查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return false; // 用户名不存在
        }
        // 2. 对比密码（明文密码 vs 加密密码）
        return passwordEncoder.matches(password, user.getPassword());
    }
}