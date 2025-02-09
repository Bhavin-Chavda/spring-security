package com.example.Security08022025.controller;

import com.example.Security08022025.dto.AuthRequest;
import com.example.Security08022025.entity.UserInfo;
import com.example.Security08022025.service.JwtService;
import com.example.Security08022025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/new")
    public UserInfo addUser(@RequestBody UserInfo userInfo)
    {
        return userService.addUser(userInfo);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest)
    {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getUsername());

        }
        else{
            throw new UsernameNotFoundException("User Not Found : "+authRequest.toString());
        }
    }

}
