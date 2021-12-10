package ru.razbejkin.electronicQueue.controller.version2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.razbejkin.electronicQueue.dto.PersonDto;
@Data
@AllArgsConstructor
public class ResponseCreatePerson {

    private String message;
    private PersonDto personDto;
}
