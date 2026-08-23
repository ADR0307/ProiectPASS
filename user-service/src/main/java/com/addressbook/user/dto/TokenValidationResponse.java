package com.addressbook.user.dto;

public record TokenValidationResponse(
        boolean valid,
        String email
) {}