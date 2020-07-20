package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.entities.Basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepository extends JpaRepository<Basic, Long> {
}
