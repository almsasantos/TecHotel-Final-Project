package com.ironhack.activityservice.model.entities;

import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.enums.MassageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ActivityTest {
    private Massage massage;

    @BeforeEach
    void setUp() {
        massage = new Massage();
        massage = new Massage(1L, 2, MassageType.AROMATHERAPY);
    }

    @AfterEach
    void tearDown() {
        massage = null;
    }

    @Test
    void getActivityId() {
        massage.setActivityId(1L);

        assertEquals(1L, massage.getActivityId());
    }

    @Test
    void getUserId() {
        massage.setUserId(2L);

        assertEquals(2L, massage.getUserId());
    }

    @Test
    void getRoomId() {
        massage.setRoomId(3);

        assertEquals(3, massage.getRoomId());
    }

    @Test
    void getTotalPrice() {
        massage.setTotalPrice(new Money(new BigDecimal("9.99")));

        assertEquals(new Money(new BigDecimal("9.99")), massage.getTotalPrice());
    }
}