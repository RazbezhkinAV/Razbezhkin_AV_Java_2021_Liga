package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Person;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PersonService {

    PersonDto showMyInfo();

    List<TicketDto> showMyTicket();

    String onlineRegisterTicket(String time);

    List<TicketDto> showFreeTicket();

    List<PersonDto> showLiveQueue();

    String getToLine();

    String getOutOfQueue();

    Person findByPhoneNumber(String phoneNumber);

    String confirmTicket(String phone, String time);
}
