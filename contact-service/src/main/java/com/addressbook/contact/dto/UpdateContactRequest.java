package com.addressbook.contact.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateContactRequest(

        @NotBlank String firstName,
        @NotBlank String lastName,

        String phone,
        String email,
        String company,

        String address,
        String picture

) {}