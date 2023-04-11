package com.Intership.FamilyBudget.dto;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ErrorResponseDTO {
    private String message;
    private List<String> errors;

    public ErrorResponseDTO(String message, List<FieldError> fieldErrors) {
        this.message = message;
        this.errors = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    }
}
