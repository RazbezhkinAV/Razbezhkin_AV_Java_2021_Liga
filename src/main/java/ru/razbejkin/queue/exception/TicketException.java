package ru.razbejkin.queue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TicketException extends RuntimeException{
    public TicketException(String message) {
        super(message);
    }
}
