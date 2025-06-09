package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.User;
import com.veterinaria.usuarios.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByEnabledTrue();

    @Query("{'$or': [{'firstName': {$regex: ?0, $options: 'i'}}, {'lastName': {$regex: ?0, $options: 'i'}}, {'username': {$regex: ?0, $options: 'i'}}]}")
    List<User> findByNameContainingIgnoreCase(String name);

    @Query("{'createdAt': {$gte: ?0, $lte: ?1}}")
    List<User> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}