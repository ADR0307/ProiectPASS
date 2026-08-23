package com.addressbook.user.service;

import com.addressbook.user.dto.AuthResponse;
import com.addressbook.user.dto.LoginRequest;
import com.addressbook.user.dto.RegisterRequest;
import com.addressbook.user.dto.UserSignupEvent;
import com.addressbook.user.entity.User;
import com.addressbook.user.kafka.UserSignupProducer;
import com.addressbook.user.repository.UserRepository;
import com.addressbook.user.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserSignupProducer producer;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        producer.publish(
                new UserSignupEvent(
                        user.getEmail(),
                        LocalDateTime.now()
                )
        );
    }

    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        UserDetails user =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getUsername()
        );
    }
}