package com.ironhack.bookservice.service;

import com.ironhack.bookservice.model.SuiteRoom;
import com.ironhack.bookservice.repository.SuiteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SuiteServiceTest {
    @Autowired
    private SuiteService suiteService;

    @Autowired
    private SuiteRepository suiteRepository;

    private SuiteRoom suiteRoom;

    @BeforeEach
    void setUp() {
        suiteRoom = new SuiteRoom(2, new BigDecimal("89.99"));
        suiteRepository.save(suiteRoom);
    }

    @AfterEach
    void tearDown() {
        suiteRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, suiteService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(2, suiteService.findById(suiteRoom.getRoomId()).getNumberOfBeds());
    }

    @Test
    void updateSuiteAvailability() {
        suiteService.updateSuiteAvailability(suiteRoom.getRoomId(), true);
        assertEquals(true, suiteService.findById(suiteRoom.getRoomId()).getAvailable());
    }

    @Test
    void createSuite() {
        SuiteRoom suiteRoom1 = new SuiteRoom(2, new BigDecimal("109.99"));
        suiteService.createSuite(suiteRoom1);
        assertEquals(2, suiteService.findAll().size());
    }
}