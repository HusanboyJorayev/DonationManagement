package com.example.donationmanagement.forplantingtrees;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "forPlantingTrees")
public class ForPlantingTrees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String cardNumber;
    private Double sendMoney;
    private Integer userId;

    private LocalDateTime createdAt;

}
