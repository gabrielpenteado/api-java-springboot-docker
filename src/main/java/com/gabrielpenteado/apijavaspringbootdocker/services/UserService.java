package com.gabrielpenteado.apijavaspringbootdocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;
import com.gabrielpenteado.apijavaspringbootdocker.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsersService() {
        return userRepository.findAll();
    }

    public UserModel saveUserService(UserModel userModel) {
        if (userModel != null) {
            userRepository.save(userModel);
        }
        return userModel;
    }
}
