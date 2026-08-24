package com.addressbook.contact.client;

import com.addressbook.contact.dto.TokenValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserServiceClient {

    private final RestClient restClient;

    @Value("${user-service.url}")
    private String url;

    public UserServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public TokenValidationResponse validate(String bearerToken) {

        return restClient.get()
                .uri(url + "/api/auth/validate")
                .header("Authorization", bearerToken)
                .retrieve()
                .body(TokenValidationResponse.class);

    }

}