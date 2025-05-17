package com.example.Best.Practice.controller;


import com.example.Best.Practice.dto.LoginRequest;
import com.example.Best.Practice.dto.SignupRequest;
import com.example.Best.Practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request){
        return userService.registerUser(request);

    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest.getEmail(),loginRequest.getPassword());

    }

}
