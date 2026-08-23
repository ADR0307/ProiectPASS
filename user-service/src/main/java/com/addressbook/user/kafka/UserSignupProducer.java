package com.addressbook.user.kafka;

import com.addressbook.user.dto.UserSignupEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignupProducer {

    private final KafkaTemplate<String, UserSignupEvent> kafkaTemplate;

    public void publish(UserSignupEvent event) {

        kafkaTemplate.send("user.signup", event);

    }

}