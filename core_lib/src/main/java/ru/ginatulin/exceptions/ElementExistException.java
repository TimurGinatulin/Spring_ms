package ru.ginatulin.exceptions;

public class ElementExistException extends RuntimeException {
    public ElementExistException(String message) {
        super(message);
    }
}