package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.model.User;
import java.util.Set;
import java.util.Optional;

public interface UserService {
    User createUser(String username, String password, Set<String> roles);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    User updateUser(String id, User user);
    void deleteUser(String id);
}