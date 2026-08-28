package com.addressbook.contact.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateContactRequest(

        @NotBlank String firstName,
        @NotBlank String lastName,

        String phone,
        String email,
        String company,

        String address,
        String picture

) {}