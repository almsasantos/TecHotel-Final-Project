package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.entities.Basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository of Basic
 */
@Repository
public interface BasicRepository extends JpaRepository<Basic, Long> {
    @Query(value = "SELECT id FROM basic_user WHERE username = :param", nativeQuery = true)
    public Long findBasicByUsername(@Param("param") String username);
}
