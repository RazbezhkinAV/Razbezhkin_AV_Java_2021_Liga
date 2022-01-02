package ru.razbejkin.queue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PersonException extends RuntimeException{
    public PersonException(String message) {
        super(message);
    }
}
