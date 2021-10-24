package ru.razbejkin.electronicQueue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.razbejkin.electronicQueue.entity.Ticket;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer> {

    Ticket findByTime(String time);

    void deleteByTime(String time);

}
