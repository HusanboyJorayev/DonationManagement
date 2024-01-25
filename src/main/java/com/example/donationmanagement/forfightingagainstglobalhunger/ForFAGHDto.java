package com.example.donationmanagement.forfightingagainstglobalhunger;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForFAGHDto {

    private Integer id;
    private String username;
    private String cardNumber;
    private Double sendMoney;
    private Integer userId;

    private LocalDateTime createdAt;
}
