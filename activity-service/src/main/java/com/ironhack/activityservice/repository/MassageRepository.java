package com.ironhack.activityservice.repository;

import com.ironhack.activityservice.model.entities.Massage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassageRepository extends JpaRepository<Massage, Long> {
}
