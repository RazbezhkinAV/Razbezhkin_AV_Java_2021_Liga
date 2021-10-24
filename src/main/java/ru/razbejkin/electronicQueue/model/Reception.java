package ru.razbejkin.electronicQueue.model;

import lombok.Data;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;

@Data
public class Reception {

    private PersonDto personDto;
    private boolean free;
    private TicketDto ticketDto;

    public Reception() {
        this.personDto = null;
        this.ticketDto = null;
        this.free = true;
    }
}
