package ru.razbejkin.electronicQueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.razbejkin.electronicQueue.entity.Ticket;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, UUID> {

    Ticket findByTime(String time);
}
