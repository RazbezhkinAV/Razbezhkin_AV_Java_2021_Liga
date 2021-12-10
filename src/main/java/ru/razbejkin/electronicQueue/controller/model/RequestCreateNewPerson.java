package ru.razbejkin.electronicQueue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestCreateNewPerson {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
