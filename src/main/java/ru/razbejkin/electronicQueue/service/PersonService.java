package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.controller.model.RequestCreateNewPerson;

import java.util.List;

public interface PersonService {
    PersonDto showMyInfo();
    List<TicketDto> showMyTicket();
    Ticket onlineRegisterTicket(String time) throws Exception;
    PersonDto savePerson(RequestCreateNewPerson person);
    boolean checkPersonByLogin(String login);
}
