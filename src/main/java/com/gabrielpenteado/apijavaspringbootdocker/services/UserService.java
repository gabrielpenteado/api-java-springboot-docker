package com.gabrielpenteado.apijavaspringbootdocker.services;

import java.util.List;
import java.util.UUID;

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

    public UserModel getUserService(String email) {
        return userRepository.findByEmail(email);

    }

    public UserModel saveUserService(UserModel userModel) {
        if (userModel != null) {
            userRepository.save(userModel);
        }
        return userModel;
    }

    public String deleteUserByIdService(UUID id) {
        boolean userIdTodelete = id != null ? userRepository.existsById(id) : false;
        if (userIdTodelete && (id != null)) {
            userRepository.deleteById(id);
            return "User ID " + id + " was deleted!";
        } else {
            return "User ID not found...";
        }

    }

    public String deleteUserByEmailService(String email) {
        UserModel userToDelete = userRepository.findByEmail(email);
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
            return "User of email " + email + " was deleted!";
        } else {
            return "User email not found...";
        }
    }
}
