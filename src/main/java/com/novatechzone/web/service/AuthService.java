package com.novatechzone.web.service;

import com.novatechzone.web.dto.UserDTO;
import com.novatechzone.web.dto.UserLoginDTO;
import com.novatechzone.web.model.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public ResponseEntity<?> registerUser(UserDTO userDTO);

    public ResponseEntity<?> registerAdmin(UserDTO userDTO, UserRole userRole);

    public ResponseEntity<?> logIn(UserLoginDTO userLoginDTO);
}
