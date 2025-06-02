package com.veterinaria.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication

public class GestionUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionUsuariosApplication.class, args);
    }
}