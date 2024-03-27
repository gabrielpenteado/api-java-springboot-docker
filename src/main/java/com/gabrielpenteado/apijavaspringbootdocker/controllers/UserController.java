package com.gabrielpenteado.apijavaspringbootdocker.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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

    @GetMapping("user/email")
    public ResponseEntity<Object> getUserByEmailController(@RequestParam String email) {
        return userService.getUserByEmailService(email);
    }

    // routes for spring boot security
    @GetMapping("/cookies")
    public String getCookies(@AuthenticationPrincipal OidcUser userLogged) {
        return String.format("""
                    <h1>OAuth2</h1>
                    <h3>principal: %s</h3>
                    <h3>Email attribute: %s</h3>
                    <h3>Authorities: %s</h3>
                    <h3>JWT: %s</h3>
                """, userLogged, userLogged.getAttribute("email"), userLogged.getAuthorities(),
                userLogged.getIdToken().getTokenValue());

    }

    // @GetMapping("/jwt")
    // public String jwt(@AuthenticationPrincipal Jwt jwt) {
    // return String.format("""
    // principal: %s\n
    // Email attribute: %s\n
    // JWT: %s\n
    // """, jwt.Claims(), jwt.getClaim(claim: "email"), jwt.getTokenValue());

    // }

    @GetMapping("/jwt")
    public String jwt(@AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt) {
        return String.format("""
                principal: %s\n
                Email attribute: %s\n
                    JWT: %s\n
                """, jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());

    }

    @GetMapping("user/{id}")
    public ResponseEntity<Object> getUserByIdController(@PathVariable("id") UUID id) {
        return userService.getUserByIdService(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveUserController(@RequestBody @Valid UserRecordDto userRecordDto) {
        return userService.saveUserService(userRecordDto);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateUserController(@PathVariable UUID id,
            @RequestBody @Valid UserRecordDto userRecordDto) {
        return userService.updateUserService(id, userRecordDto);

    }

    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteUserByIdController(@PathVariable("id") UUID id) {
        return userService.deleteUserByIdService(id);

    }

    @DeleteMapping("deletebyemail")
    public ResponseEntity<String> deleteUserByEmailController(@RequestParam String email) {
        return userService.deleteUserByEmailService(email);
    }

}
