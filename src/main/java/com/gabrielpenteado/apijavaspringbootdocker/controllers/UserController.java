package com.gabrielpenteado.apijavaspringbootdocker.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielpenteado.apijavaspringbootdocker.dto.UserRecordDto;
import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;
import com.gabrielpenteado.apijavaspringbootdocker.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUsersController() {
        return userService.getAllUsersService();
    }

    @GetMapping("user")
    public ResponseEntity<Object> getUserByEmailController(@RequestParam String email) {
        return userService.getUserByEmailService(email);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Object> getUserByIdController(@PathVariable("id") UUID id) {
        return userService.getUserByIdService(id);
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> saveUserController(@RequestBody @Valid UserRecordDto userRecordDto) {
        return userService.saveUserService(userRecordDto);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateUserController(@PathVariable UUID id,
            @RequestBody @Valid UserRecordDto userRecordDto) {
        return userService.updateUserService(id, userRecordDto);

    }

    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<Object> deleteUserByIdController(@PathVariable("id") UUID id) {
        return userService.deleteUserByIdService(id);

    }

    @DeleteMapping("deletebyemail")
    public ResponseEntity<Object> deleteUserByEmailController(@RequestParam String email) {
        return userService.deleteUserByEmailService(email);
    }

}
