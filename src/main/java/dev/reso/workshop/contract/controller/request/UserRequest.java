package dev.reso.workshop.contract.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Name cannot be null or empty")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email cannot be null or empty")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        String email,

        @NotBlank(message = "Password cannot be null or empty")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password
) {
}
