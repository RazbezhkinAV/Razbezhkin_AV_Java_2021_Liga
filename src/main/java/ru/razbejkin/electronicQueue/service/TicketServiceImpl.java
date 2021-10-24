package ru.razbejkin.electronicQueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.dao.TicketDao;
import ru.razbejkin.electronicQueue.entity.Ticket;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;

    @Override
    public Ticket findByTime(String time) {
        return ticketDao.findByTime(time);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketDao.delete(ticket);
    }

    public Ticket findById(Integer id) {
        return ticketDao.getById(id);
    }

    @Override
    public void deleteByTime(String time) {
        ticketDao.deleteByTime(time);
    }
}
