package ru.razbejkin.electronicQueue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.razbejkin.electronicQueue.dto.PersonDto;
@Data
@AllArgsConstructor
public class ResponseCreatePerson {

    private String message;
    private PersonDto personDto;
}
