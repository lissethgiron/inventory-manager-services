package com.lisseth.inventory.common.application.controllers;

import com.lisseth.inventory.common.application.controllers.util.JwtUtil;
import com.lisseth.inventory.common.application.models.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest login) {
        String user = "admin";
        String pass = "1020";

        if (!user.equals(login.getUsername()) && !pass.equals(login.getPassword())) {
            Map<String, String> error = new HashMap<>();
            error.put("status", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
            error.put("title", "UNAUTHORIZED");
            error.put("detail", "The username or password is incorrect");
            return ResponseController.error("login", HttpStatus.UNAUTHORIZED.value(), error);
        }

        String token = JwtUtil.generateToken(login.getUsername());
        return ResponseController.success("login", "login-1", token);
    }
}
