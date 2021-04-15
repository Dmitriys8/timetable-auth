package ru.sber.edu.timetableauth.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sber.edu.timetableauth.rest.exceptions.HttpUnauthorizedException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({HttpUnauthorizedException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    Map<String, String> unauthorizedAccess(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();
        exception.put("code", "401");
        exception.put("status", "Unauthorized");
        exception.put("message", e.getMessage());
        return exception;
    }
}
