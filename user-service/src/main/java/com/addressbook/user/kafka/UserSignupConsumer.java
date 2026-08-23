package com.addressbook.user.kafka;

import com.addressbook.user.dto.UserSignupEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserSignupConsumer {

    @KafkaListener(
            topics = "user.signup",
            groupId = "user-service"
    )
    public void consume(UserSignupEvent event) {

        log.info("NEW USER REGISTERED: {}", event.email());

    }

}
