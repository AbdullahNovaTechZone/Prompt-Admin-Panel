package com.novatechzone.web.service.serviceImpl;

import com.novatechzone.web.dto.PromptTypeDTO;
import com.novatechzone.web.model.PromptType;
import com.novatechzone.web.repository.PromptTypeRepository;
import com.novatechzone.web.service.PromptTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromptTypeServiceImpl implements PromptTypeService {
    @Autowired
    PromptTypeRepository promptTypeRepository;

    @Override
    public ResponseEntity<?> createPromptType(PromptTypeDTO promptTypeDTO) {
        if (promptTypeDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Prompt Type");
        } else if (promptTypeRepository.findByName(promptTypeDTO.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Prompt Type Already Exist");
        } else {
            PromptType promptType = new PromptType();
            promptType.setName(promptTypeDTO.getName());
            promptTypeRepository.save(promptType);
            return ResponseEntity.status(HttpStatus.CREATED).body("Prompt Type Created Successfully!");
        }
    }

    @Override
    public List<PromptType> readPromptTypes() {
        return promptTypeRepository.findAll();
    }

    @Override
    public ResponseEntity<?> updatePromptType(Long id, PromptTypeDTO promptTypeDTO) {
        if (promptTypeDTO.getName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter Prompt Type");
        } else if (promptTypeRepository.findByName(promptTypeDTO.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Prompt Type Already Exist");
        } else {
            Optional<PromptType> optionalPromptType = promptTypeRepository.findById(id);
            if (optionalPromptType.isPresent()) {
                PromptType promptType = optionalPromptType.get();
                promptType.setName(promptTypeDTO.getName());
                promptTypeRepository.save(promptType);
                return ResponseEntity.status(HttpStatus.OK).body("Prompt Type Updated Successfully!");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Prompt Type");
        }
    }

    @Override
    public ResponseEntity<?> deletePromptType(Long id) {
        Optional<PromptType> optionalPromptType = promptTypeRepository.findById(id);
        if (optionalPromptType.isPresent()) {
            PromptType promptType = optionalPromptType.get();
            promptTypeRepository.delete(promptType);
            return ResponseEntity.status(HttpStatus.OK).body("Prompt Type Deleted Successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Prompt Type");
    }

    @Override
    public Long getPromptTypeCount() {
        return promptTypeRepository.count();
    }
}
