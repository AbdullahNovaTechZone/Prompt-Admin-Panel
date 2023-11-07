package com.novatechzone.web.controller;

import com.novatechzone.web.dto.RequestMetaDTO;
import com.novatechzone.web.dto.UserDTO;
import com.novatechzone.web.dto.UserLoginDTO;
import com.novatechzone.web.dto.UserUpdateDTO;
import com.novatechzone.web.model.UserRole;
import com.novatechzone.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RequestMetaDTO requestMetaDTO;

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(id, userUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}
