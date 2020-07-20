package com.ironhack.securityservice.repository;

import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find all Users by username
     * @param username String username
     * @return Optional User
     */
    Optional<User> findAllByUsername(String username);

    /**
     * Find all Users by role
     * @param rol Role rol
     * @return List of Users
     */
    List<User> findByRolEquals(Role rol);
}