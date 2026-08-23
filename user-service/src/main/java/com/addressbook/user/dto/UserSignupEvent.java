package com.addressbook.user.dto;

import java.time.LocalDateTime;

public record UserSignupEvent(
        String email,
        LocalDateTime createdAt
) {}
