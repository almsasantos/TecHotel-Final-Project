package com.ironhack.bookservice.repository;

import com.ironhack.bookservice.model.SuiteRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuiteRepository extends JpaRepository<SuiteRoom, Integer> {
}
