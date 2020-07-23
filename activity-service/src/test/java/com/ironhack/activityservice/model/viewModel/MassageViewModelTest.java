package com.ironhack.activityservice.model.viewModel;

import com.ironhack.activityservice.model.enums.MassageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MassageViewModelTest {
    private MassageViewModel massageViewModel;

    @BeforeEach
    void setUp() {
        massageViewModel = new MassageViewModel();
    }

    @AfterEach
    void tearDown() {
        massageViewModel = null;
    }

    @Test
    void getUserId() {
        massageViewModel.setUserId(1L);

        assertEquals(1L, massageViewModel.getUserId());
    }

    @Test
    void getRoomId() {
        massageViewModel.setRoomId(1);

        assertEquals(1, massageViewModel.getRoomId());
    }

    @Test
    void getMassageType() {
        massageViewModel.setMassageType(MassageType.COUPLES);

        assertEquals(MassageType.COUPLES, massageViewModel.getMassageType());
    }

    @Test
    void getBeginOfActivity() {
        LocalDateTime now = LocalDateTime.now();
        massageViewModel.setBeginOfActivity(now);

        assertEquals(now, massageViewModel.getBeginOfActivity());
    }
}