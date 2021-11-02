package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.entity.Ticket;

import java.util.UUID;

public interface TicketService {

    Ticket findByTime(String time);

    void delete(Ticket ticket);

    Ticket findById(UUID id);

    void deleteByTime(String time);
}
