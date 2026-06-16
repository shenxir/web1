package com.exp1.admin.controller;

import com.exp1.admin.model.ApiResponse;
import com.exp1.admin.model.User;
import com.exp1.admin.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name) {
        Map<String, Object> result;
        if (name != null && !name.trim().isEmpty()) {
            result = userService.getPageByName(name.trim(), page, pageSize);
        } else {
            result = userService.getPage(page, pageSize);
        }
        return ApiResponse.ok("获取成功", result);
    }

    @GetMapping("/{name}")
    public ApiResponse getUserByName(@PathVariable String name) {
        Map<String, Object> user = userService.getByName(name);
        if (user != null) {
            return ApiResponse.ok("获取成功", user);
        }
        return ApiResponse.fail("用户不存在");
    }

    @PutMapping("/profile")
    public ApiResponse updateProfile(@RequestBody User user) {
        if (userService.updateProfile(user)) {
            return ApiResponse.ok("信息更新成功");
        }
        return ApiResponse.fail("信息更新失败");
    }
}
