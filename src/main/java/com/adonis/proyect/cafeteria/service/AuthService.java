package com.adonis.proyect.cafeteria.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean validate(String user, String pass) {
        return "admin".equals(user) && "secret".equals(pass);
    }
}