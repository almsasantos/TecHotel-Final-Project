package com.ironhack.activityservice.repository;

import com.ironhack.activityservice.model.entities.PoolRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolRentRepository extends JpaRepository<PoolRent, Long> {
}
