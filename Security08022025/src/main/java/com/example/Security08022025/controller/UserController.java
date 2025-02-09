package com.example.Security08022025.controller;

import com.example.Security08022025.entity.UserInfo;
import com.example.Security08022025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public UserInfo addUser(@RequestBody UserInfo userInfo)
    {
        return userService.addUser(userInfo);
    }

}
