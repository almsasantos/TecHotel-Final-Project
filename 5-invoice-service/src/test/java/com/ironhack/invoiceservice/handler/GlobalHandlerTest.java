package com.ironhack.invoiceservice.handler;

import com.ironhack.invoiceservice.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GlobalHandlerTest {

    GlobalHandler globalHandler = new GlobalHandler();

    @Test
    void handleDataIdNotFoundException() {
        assertThrows(Exception.class, () ->  globalHandler.handleDataIdNotFoundException(new DataNotFoundException("Not found."), null));
    }
}