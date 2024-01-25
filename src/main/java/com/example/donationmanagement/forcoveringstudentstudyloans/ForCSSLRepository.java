package com.example.donationmanagement.forcoveringstudentstudyloans;

import com.example.donationmanagement.forplantingtrees.ForPlantingTrees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForCSSLRepository extends JpaRepository<ForCSSL, Integer> {

    Optional<ForCSSL> findById(Integer id);

    @Query("""
            select f from ForCSSL as f
            """)
    List<ForCSSL> getAllForPlantingTrees();
}
