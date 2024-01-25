package com.example.donationmanagement.user;


import com.example.donationmanagement.cart.Cart;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Cart>carts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

