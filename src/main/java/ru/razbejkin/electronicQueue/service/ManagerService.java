package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.model.Reception;

import java.util.List;

public interface ManagerService {

    List<TicketDto> showAllTicket();

    String callNextInLiveQueue();

    Reception checkReception();

    String registrationVisit(String personPhone);

    TicketDto getNearActiveTicket();

    List<PersonDto> getLiveQueue();

    String removeTicket(String time);

    String endMeeting();
}
