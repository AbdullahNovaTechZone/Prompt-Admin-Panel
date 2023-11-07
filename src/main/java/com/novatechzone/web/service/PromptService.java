package com.novatechzone.web.service;

import com.novatechzone.web.dto.PromptDTO;
import com.novatechzone.web.model.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromptService {
    public ResponseEntity<?> createPrompt(PromptDTO promptDTO);

    public List<Prompt> readPrompts();

    public ResponseEntity<?> updatePrompt(Long id, PromptDTO promptDTO);

    public ResponseEntity<?> deletePrompt(Long id);

    public Long getPromptCount();
}
