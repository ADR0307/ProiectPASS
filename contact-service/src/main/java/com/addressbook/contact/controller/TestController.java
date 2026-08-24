package com.addressbook.contact.controller;

import com.addressbook.contact.client.UserServiceClient;
import com.addressbook.contact.dto.TokenValidationResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final UserServiceClient client;

    public TestController(UserServiceClient client) {
        this.client = client;
    }

    @GetMapping
    public TokenValidationResponse validate(
            @RequestHeader("Authorization") String token) {

        return client.validate(token);
    }
}