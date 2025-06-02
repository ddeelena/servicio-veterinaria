package com.veterinaria.usuarios.security.controller;

import com.veterinaria.usuarios.security.dto.AuthRequest;
import com.veterinaria.usuarios.security.dto.AuthResponse;
import com.veterinaria.usuarios.security.dto.RegisterRequest;
import com.veterinaria.usuarios.security.jwt.JwtTokenUtil;
import com.veterinaria.usuarios.security.model.UserAuth;
import com.veterinaria.usuarios.security.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAuth userDetails = (UserAuth) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt, userDetails.getUsername(), userDetails.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userAuthRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso");
        }

        UserAuth user = new UserAuth();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        userAuthRepository.save(user);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}