package ru.razbejkin.queue.service;

import ru.razbejkin.queue.dto.PersonDto;
import ru.razbejkin.queue.dto.TicketDto;
import ru.razbejkin.queue.entity.Person;
import ru.razbejkin.queue.entity.Ticket;

import java.util.List;

public interface PersonService {
    PersonDto showMyInfo();
    List<TicketDto> showMyTicket();
    Ticket onlineRegisterTicket(String time);
    Person savePerson(Person person);
    Person updatePerson(Person updatePerson);
    void deletePerson();
}
