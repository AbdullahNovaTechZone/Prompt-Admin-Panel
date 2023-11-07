package com.novatechzone.web.controller;

import com.novatechzone.web.dto.UserDTO;
import com.novatechzone.web.dto.UserLoginDTO;
import com.novatechzone.web.model.UserRole;
import com.novatechzone.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public String logInIndex() {
        return "LogIn";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody UserDTO userDTO) {
        UserRole userRole = UserRole.ADMIN;
        return authService.registerAdmin(userDTO, userRole);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.logIn(userLoginDTO);
    }
}
