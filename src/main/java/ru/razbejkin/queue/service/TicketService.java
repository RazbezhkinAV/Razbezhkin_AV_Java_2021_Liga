package ru.razbejkin.queue.service;

import ru.razbejkin.queue.dto.TicketDto;
import ru.razbejkin.queue.entity.Ticket;

import java.time.LocalTime;
import java.util.List;

public interface TicketService {

    Ticket findByTime(String time);

    List<TicketDto> showAllTicket();

    List<TicketDto> showFreeTicket();

    TicketDto getNearActiveTicket();

    void removeTicket(String time);

    TicketDto confirmTicket(String id);

    void lateForVisit(LocalTime localTime);

    void chekConfirm(TicketDto ticketDto);

    List<Ticket> getAll();

    Ticket getTicket(String id);

    void chekActualTime(LocalTime currentTime);

}
