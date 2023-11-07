package com.novatechzone.web.service;

import com.novatechzone.web.dto.PromptTypeDTO;
import com.novatechzone.web.model.PromptType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromptTypeService {
    public ResponseEntity<?> createPromptType(PromptTypeDTO promptTypeDTO);

    public List<PromptType> readPromptTypes();

    public ResponseEntity<?> updatePromptType(Long id, PromptTypeDTO promptTypeDTO);

    public ResponseEntity<?> deletePromptType(Long id);

    public Long getPromptTypeCount();
}
