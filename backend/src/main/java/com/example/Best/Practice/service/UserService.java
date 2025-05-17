package com.example.Best.Practice.service;


import com.example.Best.Practice.dto.SignupRequest;
import com.example.Best.Practice.entity.User;
import com.example.Best.Practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public String registerUser(SignupRequest request){
        if (userRepository.existsByEmail((request.getEmail()))){
            return "Email already registered!";

        }

        //New user create account  so db can stored this user detail's
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return "user registerd successfully";
    }




    public String loginUser(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()){
            return "User not found";

        }
        User user =  optionalUser.get();

        if (!user.getPassword().equals(password)){
            return "Invalid password";
        }
        return "login Successful";
    }
}
