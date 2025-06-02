package com.veterinaria.usuarios.security.repository;

import com.veterinaria.usuarios.security.model.UserAuth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends MongoRepository<UserAuth, String> {
    Optional<UserAuth> findByUsername(String username);
}