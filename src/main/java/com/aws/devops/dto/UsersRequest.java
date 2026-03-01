package com.aws.devops.dto;

public record UsersRequest(
        String name,
        String email,
        String password
) {
}
