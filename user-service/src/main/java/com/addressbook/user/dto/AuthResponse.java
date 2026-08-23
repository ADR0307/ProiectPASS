package com.addressbook.user.dto;

public record AuthResponse(

        String token,
        String email

) {}
