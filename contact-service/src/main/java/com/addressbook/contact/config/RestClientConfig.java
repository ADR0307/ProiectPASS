package com.addressbook.contact.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient restClient(@Value("${user-service.url}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .build();
    }
}