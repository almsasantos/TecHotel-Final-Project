package com.ironhack.activityservice.repository;

import com.ironhack.activityservice.model.entities.PoolRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Pool Rent Repository
 */
@Repository
public interface PoolRentRepository extends JpaRepository<PoolRent, Long> {
    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @return a list of object
     */
    @Query(value = "SELECT * FROM pool_rents WHERE user_id = :userId", nativeQuery = true)
    public List<PoolRent> filterPoolRentByUserId(@Param("userId") Long userId);

}
