package dev.reso.workshop.contract.controller.response;

import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String name,
        String email,
        String password
) {
}
