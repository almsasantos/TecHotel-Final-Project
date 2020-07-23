package com.ironhack.bookservice.service;

import com.ironhack.bookservice.model.RegularRoom;
import com.ironhack.bookservice.repository.RegularRoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RegularRoomServiceTest {

    @Autowired
    private RegularRoomService regularRoomService;

    @Autowired
    private RegularRoomRepository regularRoomRepository;

    private RegularRoom regularRoom;

    @BeforeEach
    void setUp() {
        regularRoom = new RegularRoom(2, true, true, new BigDecimal("89.99"));
        regularRoomRepository.save(regularRoom);
    }

    @AfterEach
    void tearDown() {
        regularRoomRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, regularRoomService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(2, regularRoomService.findById(regularRoom.getRoomId()).getNumberOfBeds());
    }

    @Test
    void updateRegularRoomAvailability() {
        regularRoomService.updateRegularRoomAvailability(regularRoom.getRoomId(), true);
        assertEquals(true, regularRoomService.findById(regularRoom.getRoomId()).getAvailable());
    }

    @Test
    void createRoom() {
        RegularRoom regularRoom1 = new RegularRoom(3, true, true, new BigDecimal("99.99"));
        regularRoomService.createRoom(regularRoom1);
        assertEquals(2, regularRoomService.findAll().size());
    }
}