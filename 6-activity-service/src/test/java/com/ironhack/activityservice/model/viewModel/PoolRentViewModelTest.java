package com.ironhack.activityservice.model.viewModel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PoolRentViewModelTest {
    private PoolRentViewModel poolRentViewModel;

    @BeforeEach
    void setUp() {
        poolRentViewModel = new PoolRentViewModel();
    }

    @AfterEach
    void tearDown() {
        poolRentViewModel = null;
    }

    @Test
    void getUserId() {
        poolRentViewModel.setUserId(1L);

        assertEquals(1L, poolRentViewModel.getUserId());
    }

    @Test
    void getRoomId() {
        poolRentViewModel.setRoomId(1);

        assertEquals(1, poolRentViewModel.getRoomId());
    }

    @Test
    void getFloatiesNum() {
        poolRentViewModel.setFloatiesNum(1);

        assertEquals(1, poolRentViewModel.getFloatiesNum());
    }

    @Test
    void getTowelNum() {
        poolRentViewModel.setTowelNum(1);

        assertEquals(1, poolRentViewModel.getTowelNum());
    }
}