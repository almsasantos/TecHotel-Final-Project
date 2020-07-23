package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserClient userClient;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllBasicUserFail() {
    }

    @Test
    void findBasicUserByIdFail() {
    }

    @Test
    void createBasicUserFail() {
    }

    @Test
    void updateRoomIdFail() {
    }

    @Test
    void updateBasicBalanceFail() {
    }

    @Test
    void updateNumberOfStaysFail() {
    }

    @Test
    void deleteUserFail() {
    }

    @Test
    void findAllPremiumUsersFail() {
    }

    @Test
    void findPremiumUserByIdFail() {
    }

    @Test
    void createPremiumUserFail() {
    }

    @Test
    void createNewAdminFail() {
    }

    @Test
    void findAllAdminFail() {
    }

    @Test
    void findAdminByIdFail() {
    }

    @Test
    void findByUsernameFail() {
    }
}