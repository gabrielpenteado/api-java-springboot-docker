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

    public UserModel getUserByEmailService(String email) {
        return userRepository.findByEmail(email);

    }

    @SuppressWarnings("null")
    public UserModel getUserByIdService(UUID id) {
        UserModel user = userRepository.findById(id).get();
        return user;
    }

    public UserModel saveUserService(UserModel user) {
        if (user != null) {
            userRepository.save(user);
        }
        return user;
    }

    @SuppressWarnings("null")
    public UserModel updateUserService(UUID id, UserModel user) {
        UserModel userToUpdate = userRepository.findById(id).get();
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setName(user.getName());
        userToUpdate.setUrlAvatar(user.getUrlAvatar());

        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public String deleteUserByIdService(UUID id) {
        boolean userIdExists = id != null ? userRepository.existsById(id) : false;
        if (userIdExists && (id != null)) {
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
