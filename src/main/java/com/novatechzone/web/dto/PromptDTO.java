package com.novatechzone.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromptDTO {
    private String prompt;
    private Long typeId;
}
