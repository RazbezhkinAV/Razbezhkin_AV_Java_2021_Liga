package ru.razbejkin.queue.service.impl;


import org.springframework.stereotype.Service;
import ru.razbejkin.queue.dto.PersonDto;
import ru.razbejkin.queue.dto.TicketDto;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnlineTicketService {

    private List<TicketDto> ticketDtoList;

    public List<TicketDto> getTicketDtoList(){
        return ticketDtoList;
    }

    public OnlineTicketService() {
        ticketDtoList = new ArrayList<>();
        for (int i = 9; i < 19; i++) {
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
                .filter(TicketDto::isFree)
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

    public TicketDto findTicket(String time) {
        for (int i = 0; i < ticketDtoList.size(); i++) {
            TicketDto ticketDto = ticketDtoList.get(i);
            if (ticketDto.getTime().equals(time)) {
                return ticketDto;
            }
        }
        return null;
    }

    public void refreshTicket(TicketDto ticket) {
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

}
