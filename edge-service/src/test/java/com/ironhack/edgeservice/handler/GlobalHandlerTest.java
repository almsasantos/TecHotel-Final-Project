package com.ironhack.edgeservice.handler;

import com.ironhack.edgeservice.exceptions.SecurityMicroserviceFail;
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
    void handleSecurityMicroserviceFail() {
        assertThrows(Exception.class, () ->  globalHandler.handleSecurityMicroserviceFail(new SecurityMicroserviceFail("Not found."), null));
    }


}