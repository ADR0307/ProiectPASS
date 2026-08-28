package com.addressbook.contact.dto;

public record ContactResponse(

        Long id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String company,

        String address,
        String picture

) {}