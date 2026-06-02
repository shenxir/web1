package com.exp1.admin.controller;

import com.exp1.admin.model.ApiResponse;
import com.exp1.admin.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse getUsers() {
        return ApiResponse.ok("获取成功", userService.getAllUsers());
    }
}
