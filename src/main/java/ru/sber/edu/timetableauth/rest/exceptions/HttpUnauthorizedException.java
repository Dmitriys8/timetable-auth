package ru.sber.edu.timetableauth.rest.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@AllArgsConstructor
public class HttpUnauthorizedException extends RuntimeException{
}
