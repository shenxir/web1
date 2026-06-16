package com.exp1.admin.service;

import com.exp1.admin.mapper.UserMapper;
import com.exp1.admin.mapper.UserTMapper;
import com.exp1.admin.model.ChangePasswordRequest;
import com.exp1.admin.model.LoginRequest;
import com.exp1.admin.model.RegisterRequest;
import com.exp1.admin.model.User;
import com.exp1.admin.model.UserT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserTMapper userTMapper;
    private final UserMapper userMapper;

    public AuthService(UserTMapper userTMapper, UserMapper userMapper) {
        this.userTMapper = userTMapper;
        this.userMapper = userMapper;
    }

    public boolean login(LoginRequest request) {
        UserT user = userTMapper.selectByName(request.getUsername());
        return user != null && user.getPassword().equals(request.getPassword());
    }

    public boolean register(RegisterRequest request) {
        UserT existing = userTMapper.selectByName(request.getUsername());
        if (existing != null) {
            return false;
        }
        UserT userT = new UserT();
        userT.setName(request.getUsername());
        userT.setPassword(request.getPassword());
        userT.setEmail(request.getEmail());
        userTMapper.insert(userT);

        // 同步写入 User 表，用于用户列表展示
        User user = new User();
        user.setDate(new Date());
        user.setName(request.getUsername());
        user.setProvince("未设置");
        user.setCity("未设置");
        user.setAddress("未设置");
        user.setZip("000000");
        userMapper.insert(user);

        return true;
    }

    public boolean changePassword(ChangePasswordRequest request) {
        UserT user = userTMapper.selectByName(request.getUsername());
        if (user == null || !user.getPassword().equals(request.getOldPassword())) {
            return false;
        }
        userTMapper.updatePassword(request.getUsername(), request.getNewPassword());
        return true;
    }

    public boolean updateAvatar(String name, String avatar) {
        return userTMapper.updateAvatar(name, avatar) > 0;
    }
}
