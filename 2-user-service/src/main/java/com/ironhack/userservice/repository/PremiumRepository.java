package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.entities.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository of Premium
 */
@Repository
public interface PremiumRepository extends JpaRepository<Premium, Long> {
    @Query(value = "SELECT id FROM basic_user WHERE username = :param;", nativeQuery = true)
    public Long findPremiumByUsername(@Param("param") String username);
}
