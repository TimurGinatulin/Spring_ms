package ru.ginatulin.core.exceptions.advice;

import ru.ginatulin.core.exceptions.ElementExistException;
import ru.ginatulin.core.exceptions.NotFoundException;
import ru.ginatulin.core.exceptions.models.MarketError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handlerResourceNotFound(NotFoundException e) {
        MarketError response = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<?> handlerElementExist(ElementExistException e) {
        MarketError response = new MarketError(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}