package ru.razbejkin.queue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.queue.dto.MappingDTO;
import ru.razbejkin.queue.dto.TicketDto;
import ru.razbejkin.queue.entity.Ticket;
import ru.razbejkin.queue.exception.TicketException;
import ru.razbejkin.queue.repository.TicketRepo;
import ru.razbejkin.queue.service.TicketService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

    private final OnlineTicketService onlineTicketService;
    private final TicketRepo ticketRepo;

    @Override
    public Ticket findByTime(String time) {
        Optional<Ticket> optionalTicket = ticketRepo.findTicketByTime(time);
        if (optionalTicket.isPresent())
            throw new TicketException("Ticket for " + time + " not found!");

        return optionalTicket.get();
    }

    @Override
    public List<TicketDto> showAllTicket() {
        return onlineTicketService.getAllTicket();
    }

    @Override
    public List<TicketDto> showFreeTicket() {
        return onlineTicketService.getFreeTicket();
    }

    @Override
    public TicketDto getNearActiveTicket() {
        return onlineTicketService.getNearActiveTicket();
    }

    @Override
    @Transactional
    public void removeTicket(String time) {
        Ticket ticket = findByTime(time);
        if (ticket.isVisit())
            throw new TicketException("Ticket for " + time + " used");
        ticketRepo.delete(ticket);
        onlineTicketService.setTicketIsFree(time);
    }

    @Override
    public TicketDto confirmTicket(String id) {
        Optional<Ticket> optionalTicket = ticketRepo.findTicketById(UUID.fromString(id));
        if (optionalTicket.isPresent())
            throw new TicketException("Ticket not found ");

        Ticket ticket = optionalTicket.get();
        ticket.setConfirm(true);
        onlineTicketService.setTicketIsConfirm(ticket.getTime());
        return MappingDTO.mapToTicketDto(ticket);
    }

    @Override
    public List<Ticket> getAll(){
        return ticketRepo.findAll();
    }

    @Override
    public Ticket getTicket(String id){
        Optional<Ticket> ticket = ticketRepo.findTicketById(UUID.fromString(id));
        if(ticket.isPresent())
            throw new TicketException("Not found");
        return ticket.get();
    }

    @Override
    public void lateForVisit(LocalTime localTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = localTime.format(dtf);
        List<TicketDto> ticketDtoList = onlineTicketService.getTicketDtoList();
        for (int i = 0; i < ticketDtoList.size(); i++) {
            String time = LocalTime.parse(ticketDtoList.get(i).getTime()).plusMinutes(15).toString();
            TicketDto ticketDto = ticketDtoList.get(i);
            if (currentTime.equals(time) && !ticketDto.isVisit()) {
                removeTicket(ticketDto.getTime());
                onlineTicketService.setTicketIsFree(ticketDto.getTime());
                onlineTicketService.refreshTicket(ticketDto);
            }
        }
    }

    @Override
    public void chekConfirm(TicketDto ticketDto) {
        if (!ticketDto.isConfirm()) {
            Timer tt = new Timer();
            tt.schedule(new TimerTask() {
                @Override
                public void run() {
                    String time = ticketDto.getTime();
                    Ticket ticket = findByTime(time);
                    if (!ticket.isConfirm()) {
                        onlineTicketService.setTicketIsFree(time);
                        removeTicket(time);
                    }
                    tt.cancel();
                }
            }, 90 * 10 * 1000);
        }
    }

    public void chekActualTime(LocalTime currentTime) {
        List<TicketDto> ticketDtoList = onlineTicketService.getTicketDtoList();
        for (int i = 0; i < ticketDtoList.size(); i++) {
            LocalTime time = LocalTime.parse(ticketDtoList.get(i).getTime()).plusMinutes(30);
            if (currentTime.isAfter(time)) {
                TicketDto ticketDto = ticketDtoList.get(i);
                ticketDto.setFree(false);
                ticketDtoList.set(i, ticketDto);
            }
        }
    }

}
