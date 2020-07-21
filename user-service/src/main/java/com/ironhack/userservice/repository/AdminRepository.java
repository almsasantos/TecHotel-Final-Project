package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of Admin
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
