package com.addressbook.user.dto;

public record LoginRequest(

        String email,
        String password

) {}
