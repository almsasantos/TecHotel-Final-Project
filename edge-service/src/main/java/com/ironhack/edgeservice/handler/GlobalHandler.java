package com.ironhack.edgeservice.handler;

import com.ironhack.edgeservice.exceptions.SecurityMicroserviceFail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Global Handler of Exceptions
 */
@ControllerAdvice
public class GlobalHandler {
    /**
     * Handler of Exception in Security Microservice
     * @param e Exception type
     * @param response Response type
     * @throws IOException IOException
     */
    @ExceptionHandler(SecurityMicroserviceFail.class)
    public void handleSecurityMicroserviceFail(SecurityMicroserviceFail e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

}
