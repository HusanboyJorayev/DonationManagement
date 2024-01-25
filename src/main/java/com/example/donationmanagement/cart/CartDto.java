package com.example.donationmanagement.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto {
    private Integer id;
    @NotBlank(message = "cardNumber cannot be null or empty")
    private String cardNumber;
    @NotBlank(message = "cardNumber cannot be null or empty")
    private String cardCode;
    private Double balance;
    @NotNull(message = "userId cannot be null")
    private Integer userId;
    @NotNull(message = "validateYear cannot be null")
    private Integer validateYear;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
