package com.gabrielpenteado.apijavaspringbootdocker.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;
import com.gabrielpenteado.apijavaspringbootdocker.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserModel> getAllUsersController() {
        return userService.getAllUsersService();
    }

    @GetMapping("user")
    public UserModel getUserByEmailController(@RequestParam String email) {
        return userService.getUserByEmailService(email);
    }

    @GetMapping("user/{id}")
    public UserModel getUserByIdController(@PathVariable("id") UUID id) {
        return userService.getUserByIdService(id);
    }

    @PostMapping("/add")
    public UserModel saveUserController(@RequestBody UserModel userModel) {
        UserModel userCreated = userService.saveUserService(userModel);
        return userCreated;
    }

    @PutMapping("update/{id}")
    public UserModel updateUserController(@PathVariable UUID id, @RequestBody UserModel user) {
        UserModel userUpdated = userService.updateUserService(id, user);
        return userUpdated;
    }

    @DeleteMapping("deletebyid/{id}")
    public String deleteUserByIdController(@PathVariable("id") UUID id) {
        return userService.deleteUserByIdService(id);

    }

    @DeleteMapping("deletebyemail")
    public String deleteUserByEmailController(@RequestParam String email) {
        return userService.deleteUserByEmailService(email);
    }

}
