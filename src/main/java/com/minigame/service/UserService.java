package com.minigame.service;

import com.minigame.entity.User;
import com.minigame.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    // 注入 UserMapper（操作数据库）
    @Autowired
    private UserMapper userMapper;

    // 注册功能（明文存储密码，无加密）
    public boolean register(String username, String password) {
        try {
            // 1. 检查用户名是否已存在
            User existingUser = userMapper.selectByUsername(username);
            if (existingUser != null) {
                return false; // 用户名已存在
            }
            // 2. 直接存储明文密码
            User user = new User();
            user.setUsername(username);
            user.setPassword(password); // 明文存储
            user.setCreateTime(new Date());
            userMapper.insert(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 登录功能（明文对比密码）
    public boolean login(String username, String password) {
        try {
            // 1. 查询用户
            User user = userMapper.selectByUsername(username);
            if (user == null) {
                return false; // 用户名不存在
            }
            // 2. 明文密码直接对比
            return password.equals(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}