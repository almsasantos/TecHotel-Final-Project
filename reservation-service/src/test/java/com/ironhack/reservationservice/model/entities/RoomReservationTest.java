package com.ironhack.reservationservice.model.entities;

import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.enums.RoomType;
import com.ironhack.reservationservice.model.enums.TypeOfUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoomReservationTest {
    private RoomReservation roomReservation;

    @BeforeEach
    void setUp() {
        roomReservation = new RoomReservation();
        roomReservation = new RoomReservation(1, RoomType.REGULAR_ROOM, 2);
    }

    @AfterEach
    void tearDown() {
        roomReservation = null;
    }

    @Test
    void getRoomReservationId() {
        roomReservation.setRoomReservationId(1L);

        assertEquals(1L, roomReservation.getRoomReservationId());
    }

    @Test
    void getRoomId() {
        roomReservation.setRoomId(2);

        assertEquals(2, roomReservation.getRoomId());
    }

    @Test
    void getNumberOfNights() {
        roomReservation.setNumberOfNights(4);

        assertEquals(4, roomReservation.getNumberOfNights());
    }

    @Test
    void getTotalPrice() {
        roomReservation.setTotalPrice(new Money(new BigDecimal("99.99")));

        assertEquals(new Money(new BigDecimal("99.99")), roomReservation.getTotalPrice());
    }

    @Test
    void getCheckInDay() {
        roomReservation.setCheckInDay(LocalDate.now());

        assertEquals(LocalDate.now(), roomReservation.getCheckInDay());
    }

    @Test
    void getCheckOutDay() {
        roomReservation.setCheckOutDay(LocalDate.now());

        assertEquals(LocalDate.now(), roomReservation.getCheckOutDay());
    }

    @Test
    void getRoomType() {
        roomReservation.setRoomType(RoomType.SUITE_ROOM);

        assertEquals(RoomType.SUITE_ROOM, roomReservation.getRoomType());
    }

    @Test
    void getUserReservation() {
        UserReservation userReservation = new UserReservation();
        userReservation.setRoomReservation(roomReservation);
        userReservation.setTypeOfUser(TypeOfUser.PREMIUM);
        userReservation.setUserId(1L);
        roomReservation.setUserReservation(userReservation);

        assertEquals(userReservation, roomReservation.getUserReservation());
    }
}