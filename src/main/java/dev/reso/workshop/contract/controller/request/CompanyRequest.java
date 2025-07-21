package dev.reso.workshop.contract.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyRequest(
        @NotBlank(message = "Name cannot be null or empty")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email cannot be null or empty")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        String email,

        @NotBlank(message = "CPNJ cannot be null or empty")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CPNJ must be in the format XX.XXX.XXX/XXXX-XX")
        String cpnj,

        @NotBlank(message = "Phone cannot be null or empty")
        @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
        String phone,

        @NotBlank(message = "Address cannot be null or empty")
        @Size(max = 255, message = "Address cannot exceed 255 characters")
        String address
) {
}
