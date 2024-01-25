package com.example.donationmanagement.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequest {
    @NotBlank(message = "password Cannot be null or empty")
    private String password;
    @NotBlank(message = "phoneNumber Cannot be null or empty")
    private String email;
}
