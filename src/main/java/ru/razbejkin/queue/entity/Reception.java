package ru.razbejkin.queue.entity;

import lombok.Data;
import ru.razbejkin.queue.dto.PersonDto;
import ru.razbejkin.queue.dto.TicketDto;

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
