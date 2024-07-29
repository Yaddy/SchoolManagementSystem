package com.yadirichukwu.schoolmanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(

        @NotEmpty(message = "First name is required and should not be empty")
        String firstname,
        @NotEmpty(message = "Last name is required and should not be empty")
        String lastname,
        String email,
        Integer schoolId
) {
}
