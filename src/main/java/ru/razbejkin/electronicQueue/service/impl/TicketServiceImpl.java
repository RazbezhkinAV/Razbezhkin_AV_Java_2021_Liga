package ru.razbejkin.electronicQueue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.repository.TicketRepo;
import ru.razbejkin.electronicQueue.service.TicketService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

    private final OnlineTicketService onlineTicketService;
    private final TicketRepo ticketRepo;

    @Override
    public Ticket findByTime(String time){
        return ticketRepo.findByTime(time);
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
    public String removeTicket(String time) {
        Ticket ticket = ticketRepo.findByTime(time);
        if (ticket == null)
            return "Талон на время " + time + " отсутствует";

        if (ticket.isVisit())
            return "Невозможно отменить таллон, тк он уже использован";

        ticketRepo.delete(ticket);
        onlineTicketService.setTicketIsFree(time);

        return "Талоне отменён";
    }

    @Override
    public String confirmTicket(String id) {
        Ticket ticket = ticketRepo.findById(UUID.fromString(id)).orElse(null);

        if(ticket == null)
            return "Билет не найден";

        ticket.setConfirm(true);
        onlineTicketService.setTicketIsConfirm(ticket.getTime());

        if (ticket.isConfirm())
            return "Заявка подтверждена";
        else
            return "Заявка не подтверждена";
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

    @Override
    public void save(Ticket ticket) {
        ticketRepo.save(ticket);
    }
}
