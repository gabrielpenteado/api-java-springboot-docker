package com.gabrielpenteado.apijavaspringbootdocker.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielpenteado.apijavaspringbootdocker.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);
}
