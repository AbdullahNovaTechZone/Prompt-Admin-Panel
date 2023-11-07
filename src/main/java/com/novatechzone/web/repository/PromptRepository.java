package com.novatechzone.web.repository;

import com.novatechzone.web.model.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {
    Optional<Prompt> findByPromptAndTypeId(String prompt, Long typeId);
}
