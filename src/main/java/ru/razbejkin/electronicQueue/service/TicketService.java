package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.entity.Ticket;

public interface TicketService {

    Ticket findByTime(String time);

    void delete(Ticket ticket);

    Ticket findById(Integer id);

    void deleteByTime(String time);
}
