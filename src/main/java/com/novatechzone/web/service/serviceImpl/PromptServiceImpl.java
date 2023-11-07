package com.novatechzone.web.service.serviceImpl;

import com.novatechzone.web.dto.PromptDTO;
import com.novatechzone.web.dto.RequestMetaDTO;
import com.novatechzone.web.model.Prompt;
import com.novatechzone.web.repository.PromptRepository;
import com.novatechzone.web.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromptServiceImpl implements PromptService {
    @Autowired
    PromptRepository promptRepository;
    @Autowired
    RequestMetaDTO requestMetaDTO;

    @Override
    public ResponseEntity<?> createPrompt(PromptDTO promptDTO) {
        if (promptDTO.getPrompt().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Prompt");
        } else if (promptDTO.getTypeId().equals("0")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Select Prompt Type");
        } else if (promptRepository.findByPromptAndTypeId(promptDTO.getPrompt(), promptDTO.getTypeId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Prompt Already Exist");
        } else {
            Prompt prompt = new Prompt();
            prompt.setPrompt(promptDTO.getPrompt());
            prompt.setUserId(requestMetaDTO.getId());
            prompt.setTypeId(promptDTO.getTypeId());
            promptRepository.save(prompt);
            return ResponseEntity.status(HttpStatus.CREATED).body("Prompt Created Successfully!");
        }
    }

    @Override
    public List<Prompt> readPrompts() {
        return promptRepository.findAll();
    }

    @Override
    public ResponseEntity<?> updatePrompt(Long id, PromptDTO promptDTO) {
        if (promptDTO.getPrompt().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Prompt");
        } else if (promptDTO.getTypeId().equals("0")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Select Prompt Type");
        } else if (promptRepository.findByPromptAndTypeId(promptDTO.getPrompt(), promptDTO.getTypeId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Prompt Already Exist");
        } else {
            Optional<Prompt> optionalPrompt = promptRepository.findById(id);
            if (optionalPrompt.isPresent()) {
                Prompt prompt = optionalPrompt.get();
                prompt.setPrompt(promptDTO.getPrompt());
                prompt.setTypeId(promptDTO.getTypeId());
                promptRepository.save(prompt);
                return ResponseEntity.status(HttpStatus.OK).body("Prompt Updated Successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Prompt");
            }
        }
    }

    @Override
    public ResponseEntity<?> deletePrompt(Long id) {
        Optional<Prompt> optionalPrompt = promptRepository.findById(id);
        if (optionalPrompt.isPresent()) {
            Prompt prompt = optionalPrompt.get();
            promptRepository.delete(prompt);
            return ResponseEntity.status(HttpStatus.OK).body("Prompt Deleted Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Prompt");
        }
    }

    @Override
    public Long getPromptCount() {
        return promptRepository.count();
    }
}
