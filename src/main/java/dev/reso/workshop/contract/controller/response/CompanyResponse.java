package dev.reso.workshop.contract.controller.response;

import lombok.Builder;

@Builder
public record CompanyResponse(
        String name,
        String email,
        String cpnj,
        String phone,
        String address
) {
}
