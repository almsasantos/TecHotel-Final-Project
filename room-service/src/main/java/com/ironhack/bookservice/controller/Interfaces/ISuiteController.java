package com.ironhack.bookservice.controller.Interfaces;

import com.ironhack.bookservice.model.SuiteRoom;

import java.util.List;

public interface ISuiteController {
    public List<SuiteRoom> findAllSuites();

    public SuiteRoom findSuiteById(Integer roomId);

    public void updateSuiteAvailability(Integer roomId, Boolean availability);

    public SuiteRoom createSuite(SuiteRoom suite);
}
