package com.example.donationmanagement.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select c from Cart as c
            """)
    List<Cart> getAllCarts();
}
