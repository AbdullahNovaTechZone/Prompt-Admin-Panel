package com.novatechzone.web.controller;

import com.novatechzone.web.dto.PromptDTO;
import com.novatechzone.web.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prompt")
public class PromptController {
    @Autowired
    PromptService promptService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PromptDTO promptDTO) {
        return promptService.createPrompt(promptDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PromptDTO promptDTO) {
        return promptService.updatePrompt(id, promptDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id) {
        return promptService.deletePrompt(id);
    }
}
