package com.ironhack.userservice.handler;

import com.ironhack.userservice.exception.DataNotFoundException;
import com.ironhack.userservice.exception.UsernameExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {

    /**
     * Exception Handler to handle exceptions.
     * @param e Receives an Exception.
     * @param response Receives an HTTP Response.
     * @throws IOException
     */
    @ExceptionHandler(DataNotFoundException.class)
    public void handleDataIdNotFoundException(DataNotFoundException e, HttpServletResponse response) throws IOException{

        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    /**
     * Exception Handler to handle exceptions.
     * @param e Receives an Exception.
     * @param response Receives an HTTP Response.
     * @throws IOException
     */
    @ExceptionHandler(UsernameExistsException.class)
    public void handleUsernameExistsException(UsernameExistsException e, HttpServletResponse response) throws IOException{

        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }
}
