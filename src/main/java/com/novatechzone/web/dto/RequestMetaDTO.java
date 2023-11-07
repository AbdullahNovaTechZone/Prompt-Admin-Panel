package com.novatechzone.web.dto;

import com.novatechzone.web.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMetaDTO {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
