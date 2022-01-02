package ru.razbejkin.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.razbejkin.queue.entity.Ticket;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findTicketByTime(String time);
    Optional<Ticket> findTicketById(UUID uuid);
 }
