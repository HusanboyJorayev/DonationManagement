package com.example.donationmanagement.cart;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
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

    Page<Cart> findAllByDeletedAtIsNull(Pageable pageable);

    @Query("""
            select c from Cart  as c where c.cardCode like %:code%
            """)
    List<Cart> codeStartsWith(@Param("code") String code);

    @RestResource(path = "codeStart", rel = "codeStart")
    Page<Cart> findByCardCodeStartsWith(@Param("code") String code, Pageable pageable);


}
