package com.gabrielpenteado.apijavaspringbootdocker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;
import com.gabrielpenteado.apijavaspringbootdocker.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserModel> getAllUsersController() {
        return userService.getAllUsersService();
    }

    @PostMapping("/add")
    public UserModel saveUserController(@RequestBody UserModel userModel) {
        UserModel userCreated = userService.saveUserService(userModel);
        return userCreated;
    }

}
