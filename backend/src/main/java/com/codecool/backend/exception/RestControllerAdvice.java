package com.codecool.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleInvalidPermissionException(InvalidPermissionException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidProductException(BadRequestException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(BadRequestException ex) {
        return ex.getMessage();
    }
}

