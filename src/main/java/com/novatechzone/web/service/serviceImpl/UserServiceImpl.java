package com.novatechzone.web.service.serviceImpl;

import com.novatechzone.web.dto.UserDTO;
import com.novatechzone.web.dto.UserUpdateDTO;
import com.novatechzone.web.model.User;
import com.novatechzone.web.model.UserRole;
import com.novatechzone.web.repository.UserRepository;
import com.novatechzone.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByUserRole(UserRole.USER);
    }

    @Override
    public List<User> getAllAdmins(UserRole userRole) {
        return userRepository.findAllByUserRole(userRole);
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Name");
        } else if (userUpdateDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Password");
        } else {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setName(userUpdateDTO.getName());
                user.setPassword(userUpdateDTO.getPassword());
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("User Account Updated Successfully!");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return ResponseEntity.status(HttpStatus.OK).body("User Account Deleted Successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
    }

    @Override
    public List<User> recentUsers() {
        List<User> users = userRepository.findTop5ByUserRoleOrderByIdDesc(UserRole.USER);
        return users;
    }

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public User loadUserById(Long id) {
        return userRepository.findById(id).get();
    }
}