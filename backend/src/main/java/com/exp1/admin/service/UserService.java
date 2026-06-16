package com.exp1.admin.service;

import com.exp1.admin.mapper.UserMapper;
import com.exp1.admin.mapper.UserTMapper;
import com.exp1.admin.model.User;
import com.exp1.admin.model.UserT;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserTMapper userTMapper;

    public UserService(UserMapper userMapper, UserTMapper userTMapper) {
        this.userMapper = userMapper;
        this.userTMapper = userTMapper;
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public Map<String, Object> getPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<User> list = userMapper.selectPage(offset, pageSize);
        int total = userMapper.selectCount();
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    public Map<String, Object> getPageByName(String name, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<User> list = userMapper.selectPageByName(name, offset, pageSize);
        int total = userMapper.selectCountByName(name);
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        return result;
    }

    public Map<String, Object> getByName(String name) {
        User user = userMapper.selectByName(name);
        UserT userT = userTMapper.selectByName(name);
        if (user == null) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("date", user.getDate());
        result.put("name", user.getName());
        result.put("province", user.getProvince());
        result.put("city", user.getCity());
        result.put("address", user.getAddress());
        result.put("zip", user.getZip());
        if (userT != null) {
            result.put("avatar", userT.getAvatar());
        }
        return result;
    }

    public boolean updateProfile(User user) {
        return userMapper.updateProfile(user) > 0;
    }
}
