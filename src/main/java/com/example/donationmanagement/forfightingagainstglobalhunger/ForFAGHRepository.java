package com.example.donationmanagement.forfightingagainstglobalhunger;

import com.example.donationmanagement.forcoveringstudentstudyloans.ForCSSL;
import com.example.donationmanagement.forplantingtrees.ForPlantingTrees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForFAGHRepository extends JpaRepository<ForFAGH, Integer> {

    Optional<ForFAGH> findById(Integer id);

    @Query("""
            select f from ForFAGH as f
            """)
    List<ForFAGH> getAllForPlantingTrees();
}
