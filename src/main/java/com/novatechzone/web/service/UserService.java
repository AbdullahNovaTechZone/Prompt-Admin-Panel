package com.novatechzone.web.service;

import com.novatechzone.web.dto.UserDTO;
import com.novatechzone.web.dto.UserUpdateDTO;
import com.novatechzone.web.model.User;
import com.novatechzone.web.model.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getAllUsers();

    public ResponseEntity<?> updateUser(Long id, UserUpdateDTO userUpdateDTO);

    public ResponseEntity<?> deleteUser(Long id);

    public List<User> getAllAdmins(UserRole userRole);

    List<User> recentUsers();

    Long getUserCount();

    User loadUserById(Long id);
}
