package com.ironhack.activityservice.handler;

import com.ironhack.activityservice.exception.DataNotFoundException;
import com.ironhack.activityservice.exception.NotEnoughBalanceException;
import com.ironhack.activityservice.exception.ReservationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GlobalHandlerTest {

    GlobalHandler globalHandler = new GlobalHandler();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleDataIdNotFoundException() {
        assertThrows(Exception.class, () ->  globalHandler.handleDataIdNotFoundException(new DataNotFoundException("Not found."), null));
    }

    @Test
    void handleNotEnoughBalanceException() {
        assertThrows(Exception.class, () ->  globalHandler.handleNotEnoughBalanceException(new NotEnoughBalanceException("Not found."), null));
    }

    @Test
    void handleReservationException() {
        assertThrows(Exception.class, () ->  globalHandler.handleReservationException(new ReservationException("Not found."), null));
    }
}