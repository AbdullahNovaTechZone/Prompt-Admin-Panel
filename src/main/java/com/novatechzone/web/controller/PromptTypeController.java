package com.novatechzone.web.controller;

import com.novatechzone.web.dto.PromptTypeDTO;
import com.novatechzone.web.service.PromptTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prompt-type")
public class PromptTypeController {
    @Autowired
    PromptTypeService promptTypeService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PromptTypeDTO promptTypeDTO) {
        return promptTypeService.createPromptType(promptTypeDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PromptTypeDTO promptTypeDTO) {
        return promptTypeService.updatePromptType(id, promptTypeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id) {
        return promptTypeService.deletePromptType(id);
    }
}
