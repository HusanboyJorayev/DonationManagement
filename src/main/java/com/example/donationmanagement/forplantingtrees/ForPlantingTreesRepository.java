package com.example.donationmanagement.forplantingtrees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForPlantingTreesRepository extends JpaRepository<ForPlantingTrees, Integer> {

    Optional<ForPlantingTrees> findById(Integer id);

    @Query("""
            select f from ForPlantingTrees as f
            """)
    List<ForPlantingTrees> getAllForPlantingTrees();
}
