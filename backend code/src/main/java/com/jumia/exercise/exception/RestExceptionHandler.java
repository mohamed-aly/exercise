package com.jumia.exercise.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleIllegalArgumentException(Exception ex, HttpServletRequest request) {
        ResponseError responseError =
                ResponseError.builder().serverMessage(ex.getMessage()).title("Invalid Query Parameter").timeStamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex, HttpServletRequest request) {
        ResponseError responseError =
                ResponseError.builder().serverMessage(ex.getMessage()).title("Records Not Found").timeStamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }



}
