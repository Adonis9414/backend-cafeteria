package com.adonis.proyect.cafeteria.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adonis.proyect.cafeteria.config.JwtUtil;
import com.adonis.proyect.cafeteria.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthService authService;
    @Autowired private JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        if (authService.validate(credentials.get("username"), credentials.get("password"))) {
            // Generar token JWT
            String token = jwtUtil.generateToken(credentials.get("username"));
            logger.info("Usuario {} autenticado correctamente", credentials.get("username"));
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}