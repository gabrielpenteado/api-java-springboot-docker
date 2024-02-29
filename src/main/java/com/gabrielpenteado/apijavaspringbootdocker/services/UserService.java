package com.gabrielpenteado.apijavaspringbootdocker.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabrielpenteado.apijavaspringbootdocker.controllers.UserController;
import com.gabrielpenteado.apijavaspringbootdocker.dto.UserRecordDto;
import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;
import com.gabrielpenteado.apijavaspringbootdocker.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
    public ResponseEntity<List<UserModel>> getAllUsersService() {
        List<UserModel> usersList = userRepository.findAll();
        if (!usersList.isEmpty()) {
            for (UserModel user : usersList) {
                user.add(linkTo(methodOn(UserController.class).getUserByIdController(user.getId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersList);
    }

    public ResponseEntity<Object> getUserByEmailService(String email) {
        Optional<UserModel> userO = userRepository.findByEmail(email);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User of email " + email + " not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());

    }

    @SuppressWarnings("null")
    public ResponseEntity<Object> getUserByIdService(UUID id) {
        // ResponseEntity of type Object - use when need many types of return, in this
        // case, String and UserModel.

        Optional<UserModel> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userO.get().add(linkTo(methodOn(UserController.class).getAllUsersController()).withRel("Users List"));
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }

    public ResponseEntity<Object> saveUserService(@Valid UserRecordDto userRecordDto) {
        Optional<UserModel> userO = userRepository.findByEmail(userRecordDto.email());
        if (userO.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists.");
        }
        // Create a userModel
        var userModel = new UserModel();

        // Convert the userRecordDto from client to userModel to be saved on database
        BeanUtils.copyProperties(userRecordDto, userModel);

        // Construct the return
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @SuppressWarnings("null")
    public ResponseEntity<Object> updateUserService(UUID id, @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userModel = userO.get();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    @SuppressWarnings("null")
    public ResponseEntity<Object> deleteUserByIdService(UUID id) {
        Optional<UserModel> userO = userRepository.findById(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(userO.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");

    }

    @SuppressWarnings("null")
    public ResponseEntity<Object> deleteUserByEmailService(String email) {
        Optional<UserModel> userO = userRepository.findByEmail(email);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User of email " + email + " not found.");
        }
        userRepository.delete(userO.get());
        return ResponseEntity.status(HttpStatus.OK).body("User of email " + email + " was deleted successfully.");
    }
}
