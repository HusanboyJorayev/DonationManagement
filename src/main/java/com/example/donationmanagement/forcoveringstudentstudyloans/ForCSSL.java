package com.example.donationmanagement.forcoveringstudentstudyloans;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "forCSSL")
public class ForCSSL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String cardNumber;
    private Double sendMoney;
    private Integer userId;

    private LocalDateTime createdAt;

}
