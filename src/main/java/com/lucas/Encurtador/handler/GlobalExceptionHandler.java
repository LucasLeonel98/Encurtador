package com.lucas.Encurtador.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;


@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ExceptionMessage exceptionMessage;

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> IllegalArgumentException(IllegalArgumentException ex){
        exceptionMessage.setErrorMessage(ex.getMessage());
        exceptionMessage.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionMessage.setOccurredTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }
    @ExceptionHandler(value = BodyArgumentInvalidException.class)
    public ResponseEntity<ExceptionMessage> BodyArgumentInvalidException(BodyArgumentInvalidException ex){
        exceptionMessage.setErrorMessage(ex.getMessage());
        exceptionMessage.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionMessage.setOccurredTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }

}
