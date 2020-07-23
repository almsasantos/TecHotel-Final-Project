package com.ironhack.bookservice.controller.Interfaces;

import com.ironhack.bookservice.model.SuiteRoom;

import java.util.List;

/**
 * Suite Controller Interface
 */
public interface ISuiteController {
    /**
     * Find All Suites
     * @return a list of SuiteRoom
     */
    public List<SuiteRoom> findAllSuites();

    /**
     * Find Suite by id
     * @param roomId receives an Integer with roomId
     * @return a SuiteRoom
     */
    public SuiteRoom findSuiteById(Integer roomId);

    /**
     * Update Suite Availability
     * @param roomId receives an Integer with roomId
     * @param availability receives a Boolean with availability
     */
    public void updateSuiteAvailability(Integer roomId, Boolean availability);

    /**
     * Creates a Suite
     * @param suite receives a SuiteRoom
     * @return a SuiteRoom created
     */
    public SuiteRoom createSuite(SuiteRoom suite);
}
