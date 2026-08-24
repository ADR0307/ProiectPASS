package com.addressbook.contact.dto;

public record TokenValidationResponse(
        boolean valid,
        String email
) {}