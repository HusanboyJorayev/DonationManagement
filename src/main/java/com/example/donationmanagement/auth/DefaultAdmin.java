package com.example.donationmanagement.auth;


import com.example.donationmanagement.token.TokenRepository;
import com.example.donationmanagement.user.Role;
import com.example.donationmanagement.user.User;
import com.example.donationmanagement.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DefaultAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    @PostConstruct
    public void saveAdmin() {

        userRepository.findByEmail("admin@admin.com").ifPresent(userRepository::delete);

        User admin = User.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@admin.com")
                .phoneNumber("+998941490908")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ROLE_ADMIN)
                .createdAt(LocalDateTime.now())
                .build();

        this.userRepository.save(admin);


        System.out.println("Admin created");
    }

}
