package ru.razbejkin.queue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.razbejkin.queue.dto.PersonDto;
@Data
@AllArgsConstructor
public class ResponseCreatePerson {

    private String message;
    private PersonDto personDto;
}
