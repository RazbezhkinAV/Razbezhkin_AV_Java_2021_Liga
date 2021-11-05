package ru.razbejkin.electronicQueue.service;


import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.service.TicketService;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
public class OnlineTicketService {

    private final TicketService ticketService;

    private List<TicketDto> ticketDtoList;

    public OnlineTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
        ticketDtoList = new ArrayList<>();
        for (int i = 9; i < 22; i++) {
            if (i == 9)
                ticketDtoList.add(new TicketDto("09:00", "", true, false, false));
            else
                ticketDtoList.add(new TicketDto(i + ":00", "", true, false, false));
        }
    }

    public List<TicketDto> getAllTicket() {
        return ticketDtoList;
    }

    public List<TicketDto> getFreeTicket() {
        return ticketDtoList.stream()
                .filter(x -> !x.isConfirm())
                .filter(x -> x.isFree())
                .collect(Collectors.toList());
    }

    public List<TicketDto> getBusyTicket() {
        return ticketDtoList.stream()
                .filter(x -> !x.isFree())
                .collect(Collectors.toList());
    }

    public boolean ticketIsFree(String time) {
        for (TicketDto ticket :
                ticketDtoList) {
            if (ticket.getTime().equals(time))
                return ticket.isFree();
        }
        return false;
    }

    private TicketDto findTicket(String time) {
        for (int i = 0; i < ticketDtoList.size(); i++) {
            TicketDto ticketDto = ticketDtoList.get(i);
            if (ticketDto.getTime().equals(time)) {
                return ticketDto;
            }
        }
        return null;
    }

    private void refreshTicket(TicketDto ticket) {
        for (int i = 0; i < ticketDtoList.size(); i++) {
            TicketDto ticketDto = ticketDtoList.get(i);
            if (ticketDto.getTime().equals(ticket.getTime())) {
                ticketDtoList.set(i, ticket);
            }
        }
    }

    public void setTicketIsBusy(String time, PersonDto person) {
        TicketDto ticketDto = findTicket(time);
        if (ticketDto != null) {
            ticketDto.setFree(false);
            ticketDto.setPerson_phone(person.getPhoneNumber());
            refreshTicket(ticketDto);
        }
    }

    public void setTicketIsVisit(String time) {
        TicketDto ticketDto = findTicket(time);
        if (ticketDto != null) {
            ticketDto.setVisit(true);
            refreshTicket(ticketDto);
        }
    }

    public void setTicketIsConfirm(String time) {
        TicketDto ticketDto = findTicket(time);
        if (ticketDto != null) {
            ticketDto.setConfirm(true);
            refreshTicket(ticketDto);
        }
    }

    public void setTicketIsFree(String time) {
        TicketDto ticketDto = findTicket(time);
        if (ticketDto != null) {
            ticketDto.setFree(true);
            ticketDto.setPerson_phone("");
            ticketDto.setConfirm(false);
            ticketDto.setVisit(false);
            refreshTicket(ticketDto);
        }
    }

    public void chekActualTime(LocalTime currentTime) {
        for (int i = 0; i < ticketDtoList.size(); i++) {
            LocalTime time = LocalTime.parse(ticketDtoList.get(i).getTime()).plusMinutes(30);
            if (currentTime.isAfter(time)) {
                TicketDto ticketDto = ticketDtoList.get(i);
                ticketDto.setFree(false);
                ticketDtoList.set(i, ticketDto);
            }
        }
    }

    public TicketDto getNearActiveTicket() {
        List<TicketDto> activeTicket= getBusyTicket().stream()
                .filter(x -> x.getPerson_phone() != "")
                .filter(x -> {
                    LocalTime localTime = LocalTime.now();
                    LocalTime time = LocalTime.parse(x.getTime()).plusMinutes(30);
                    return localTime.isBefore(time);
                })
                .collect(Collectors.toList());

        if(activeTicket.isEmpty())
            return null;
        else
            return activeTicket.get(0);

    }

    public void lateForVisit(LocalTime localTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = localTime.format(dtf);
        for (int i = 0; i < ticketDtoList.size(); i++) {
            String time = LocalTime.parse(ticketDtoList.get(i).getTime()).plusMinutes(15).toString();
            TicketDto ticketDto = ticketDtoList.get(i);
            if (currentTime.equals(time) && !ticketDto.isVisit()) {
                ticketService.deleteByTime(ticketDto.getTime());
                setTicketIsFree(ticketDto.getTime());
                refreshTicket(ticketDto);
            }
        }
    }

    public void chekConfirm(TicketDto ticketDto) {
        if (!ticketDto.isConfirm()) {
            Timer tt = new Timer();
            tt.schedule(new TimerTask() {
                @Override
                public void run() {
                    String time = ticketDto.getTime();
                    Ticket ticket = ticketService.findByTime(time);
                    if (!ticket.isConfirm()) {
                        setTicketIsFree(time);
                        ticketService.delete(ticket);
                    }
                    tt.cancel();
                }
            }, 90 * 10 * 1000);
        }
    }
}
