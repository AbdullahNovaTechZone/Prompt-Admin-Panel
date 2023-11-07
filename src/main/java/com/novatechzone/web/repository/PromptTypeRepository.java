package com.novatechzone.web.repository;

import com.novatechzone.web.model.PromptType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromptTypeRepository extends JpaRepository<PromptType, Long> {
    Optional<PromptType> findByName(String name);
}
