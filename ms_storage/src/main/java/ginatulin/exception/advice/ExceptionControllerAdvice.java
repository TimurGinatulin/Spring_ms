package ginatulin.exception.advice;

import ginatulin.exception.ElementExistException;
import ginatulin.exception.NotFoundException;
import ginatulin.models.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handlerResourceNotFound(NotFoundException e) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<?> handlerElementExist(ElementExistException e) {
        Response response = new Response(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}