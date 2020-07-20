package com.ironhack.reservationservice.handler;

import com.ironhack.reservationservice.exception.DataNotFoundException;
import com.ironhack.reservationservice.exception.NotEnoughBalanceException;
import com.ironhack.reservationservice.exception.ReservationException;
import com.ironhack.reservationservice.exception.RoomNotAvailableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GlobalHandlerTest {
    GlobalHandler globalHandler = new GlobalHandler();

    @Test
    void handleDataIdNotFoundException() {
        assertThrows(Exception.class, () ->  globalHandler.handleDataIdNotFoundException(new DataNotFoundException("Not found."), null));
    }

    @Test
    void handleRoomNotAvailableException() {
        assertThrows(Exception.class, () ->  globalHandler.handleRoomNotAvailableException(new RoomNotAvailableException("Not found."), null));
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