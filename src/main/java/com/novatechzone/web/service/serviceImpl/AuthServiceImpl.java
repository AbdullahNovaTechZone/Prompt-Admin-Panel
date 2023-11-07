package com.novatechzone.web.service.serviceImpl;

import com.novatechzone.web.dto.UserDTO;
import com.novatechzone.web.dto.UserLoginDTO;
import com.novatechzone.web.model.User;
import com.novatechzone.web.model.UserRole;
import com.novatechzone.web.repository.UserRepository;
import com.novatechzone.web.service.AuthService;
import com.novatechzone.web.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> registerUser(UserDTO userDTO) {
        if (userDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Name");
        } else if (userDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Email");
        } else if (userDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Password");
        } else if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist");
        } else {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Account Created Successfully!");
        }
    }

    @Override
    public ResponseEntity<?> registerAdmin(UserDTO userDTO, UserRole userRole) {
        if (userDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Name");
        } else if (userDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Email");
        } else if (userDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Password");
        } else if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist");
        } else {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setUserRole(userRole);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin Registered Successfully!");
        }
    }

    @Override
    public ResponseEntity<?> logIn(UserLoginDTO userLoginDTO) {
        Map<String, String> data = new HashMap<>();
        if (userLoginDTO.getEmail().equals("")) {
            data.put("data", "Please Enter Email");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        } else if (userLoginDTO.getPassword().equals("")) {
            data.put("data", "Please Enter Password");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        } else {
            Optional<User> optionalUser = userRepository.findOneByEmailIgnoreCaseAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            if (optionalUser.isEmpty()) {
                data.put("data", "Invalid Credentials");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
            }
            User user = optionalUser.get();
            String accessToken = jwtUtils.generateAccessToken(user);
            data.put("data", "Log In Success");
            data.put("userRole", user.getUserRole().name());
            data.put("accessToken", accessToken);

            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
    }
}
