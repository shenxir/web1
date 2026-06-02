package com.exp1.admin.controller;

import com.exp1.admin.model.ApiResponse;
import com.exp1.admin.model.ChangePasswordRequest;
import com.exp1.admin.model.LoginRequest;
import com.exp1.admin.model.RegisterRequest;
import com.exp1.admin.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        if (authService.login(request)) {
            return ApiResponse.ok("登录成功");
        }
        return ApiResponse.fail("用户名或密码错误");
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {
        if (authService.register(request)) {
            return ApiResponse.ok("注册成功");
        }
        return ApiResponse.fail("用户名已存在");
    }

    @PostMapping("/changePassword")
    public ApiResponse changePassword(@RequestBody ChangePasswordRequest request) {
        if (authService.changePassword(request)) {
            return ApiResponse.ok("密码修改成功");
        }
        return ApiResponse.fail("用户名不存在或旧密码错误");
    }
}
