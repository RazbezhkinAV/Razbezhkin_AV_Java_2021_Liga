package ru.razbejkin.electronicQueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.repository.TicketRepo;
import ru.razbejkin.electronicQueue.entity.Ticket;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketRepo ticketRepo;

    @Override
    public Ticket findByTime(String time) {
        return ticketRepo.findByTime(time);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketRepo.delete(ticket);
    }

    public Ticket findById(UUID id) {
        return ticketRepo.getById(id);
    }

    @Override
    public void deleteByTime(String time) {
        ticketRepo.deleteByTime(time);
    }
}
