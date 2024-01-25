package com.example.donationmanagement.forfightingagainstglobalhunger;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "forFAGH")
public class ForFAGH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String cardNumber;
    private Double sendMoney;
    private Integer userId;

    private LocalDateTime createdAt;

}
