package com.ironhack.bookservice.service;

import com.ironhack.bookservice.model.SuiteRoom;
import com.ironhack.bookservice.repository.SuiteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Suite Service
 */
@Service
public class SuiteService {
    /**
     * Autowired of Suite Repository
     */
    @Autowired
    private SuiteRepository suiteRepository;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(SuiteService.class);

    /**
     * Find All Suite Rooms
     * @return a list of SuiteRoom
     */
    public List<SuiteRoom> findAll(){
        LOGGER.info("Find All Suite Hotel Rooms");
        return suiteRepository.findAll();
    }

    /**
     * Find Suite Room by id
     * @param suiteId receives an Integer with suiteId
     * @return a SuiteRoom
     */
    public SuiteRoom findById(Integer suiteId){
        LOGGER.info("Find Suite Hotel Room with id " + suiteId);
        return suiteRepository.findById(suiteId).orElseThrow(() -> new IllegalArgumentException("Suite id not found"));
    }

    /**
     * Update Suite Availability
     * @param suiteId receives an Integer with suiteId
     * @param available receives a Boolean with available
     */
    public void updateSuiteAvailability(Integer suiteId, Boolean available){
        LOGGER.info("[INIT] Update Suite Room availability with id " + suiteId +" to " + available);
        SuiteRoom suiteRoom = findById(suiteId);
        suiteRoom.setAvailable(available);
        suiteRepository.save(suiteRoom);
        LOGGER.info("[END] Update Suite Room availability with id " + suiteId +" to " + available);
    }

    /**
     * Create a new Suite
     * @param suite receives a SuiteRoom
     * @return a SuiteRoom created
     */
    public SuiteRoom createSuite(SuiteRoom suite){
        LOGGER.info("Create new Suite Hotel Room");
        return suiteRepository.save(suite);
    }
}
