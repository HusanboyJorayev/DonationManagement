package com.example.donationmanagement.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "firstname cannot be null or empty")
    private String firstname;
    @NotBlank(message = "lastname cannot be null or empty")
    private String lastname;
    @NotBlank(message = "password cannot be null or empty")
    @Column(unique = true)
    private String password;
    @NotBlank(message = "phoneNumber cannot be null or empty")
    @Column(unique = true)
    private String email;
}
