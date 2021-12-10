package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Ticket;

import java.time.LocalTime;
import java.util.List;

public interface TicketService {

    Ticket findByTime(String time);

    List<TicketDto> showAllTicket();

    List<TicketDto> showFreeTicket();

    TicketDto getNearActiveTicket();

    String removeTicket(String time);

    String confirmTicket(String id);

    void lateForVisit(LocalTime localTime);

    void chekConfirm(TicketDto ticketDto);

    void chekActualTime(LocalTime currentTime);

}
