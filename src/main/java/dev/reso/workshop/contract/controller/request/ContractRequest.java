package dev.reso.workshop.contract.controller.request;

import dev.reso.workshop.contract.entities.Manager;
import dev.reso.workshop.contract.enums.ContractType;
import dev.reso.workshop.contract.enums.Modality;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.Date;

public record ContractRequest(
        @NotBlank(message = "Name cannot be null or empty")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Min(value = 1, message = "Number must be at least 1")
        int number,

        @PositiveOrZero(message = "Balance must be positive or zero")
        double balance,

        @Positive(message = "Total must be positive")
        double total,

        @NotNull(message = "Contract type cannot be null")
        ContractType type,

        @NotBlank(message = "Rating cannot be null or empty")
        String rating,

        @NotNull(message = "Manager cannot be null")
        Manager manager,

        @NotNull(message = "Initiation contract date cannot be null")
        @PastOrPresent(message = "Initiation contract date cannot be in the future")
        Date initiationContract,

        @NotNull(message = "End contract date cannot be null")
        @FutureOrPresent(message = "End contract date cannot be in the past")
        Date endContract,

        @NotNull(message = "Modality cannot be null")
        Modality modality,

        @Valid
        @NotNull(message = "Company cannot be null")
        CompanyRequest company
                              ) {
        public ContractRequest {
            if (initiationContract != null && endContract != null && endContract.before(initiationContract)) {
                throw new IllegalArgumentException("End contract date must be after initiation contract date");
        }
    }
}
