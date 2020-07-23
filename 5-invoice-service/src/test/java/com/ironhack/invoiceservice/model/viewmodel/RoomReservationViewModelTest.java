package com.ironhack.invoiceservice.model.viewmodel;

import com.ironhack.invoiceservice.enums.RoomType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomReservationViewModelTest {
    private RoomReservationViewModel roomReservationViewModel;

    @BeforeEach
    void setUp() {
        roomReservationViewModel = new RoomReservationViewModel();
    }

    @AfterEach
    void tearDown() {
        roomReservationViewModel = null;
    }

    @Test
    void getUserId() {
        roomReservationViewModel.setUserId(1L);

        assertEquals(1L, roomReservationViewModel.getUserId());
    }

    @Test
    void getUserName() {
        roomReservationViewModel.setUserName("Ana Martins");

        assertEquals("Ana Martins", roomReservationViewModel.getUserName());
    }

    @Test
    void getRoomId() {
        roomReservationViewModel.setRoomId(1);

        assertEquals(1, roomReservationViewModel.getRoomId());
    }

    @Test
    void getRoomType() {
        roomReservationViewModel.setRoomType(RoomType.SUITE_ROOM);

        assertEquals(RoomType.SUITE_ROOM, roomReservationViewModel.getRoomType());
    }

    @Test
    void getNumberOfNights() {
        roomReservationViewModel.setNumberOfNights(2);

        assertEquals(2, roomReservationViewModel.getNumberOfNights());
    }
}