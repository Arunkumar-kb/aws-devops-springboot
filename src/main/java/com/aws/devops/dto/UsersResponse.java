package com.aws.devops.dto;

public record UsersResponse(
        Long id,
        String name,
        String email,
        String role
) {
}
