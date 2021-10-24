package ru.razbejkin.electronicQueue.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.util.MappingDTO;

@Service
@AllArgsConstructor
public class ReceptionService {

    private Reception reception;

    public boolean receptionIsFree() {
        return reception.isFree();
    }

    public String startMeeting(PersonDto person, Ticket ticket) {
        if (reception.isFree()) {
            reception.setPersonDto(person);
            reception.setFree(false);

            if (ticket == null)
                return "Начался приём у Person" + person.getName() + " " + person.getSurname() + " из очереди";

            reception.setTicketDto(MappingDTO.mapToTicketDto(ticket));

            return "Начался приём у Person" + person.getName() + " " + person.getSurname()
                    + " онлайн запись на " + ticket.getTime();

        } else {
            return "Приёмная занята занята";
        }
    }

    public String endMeeting() {
        if (!reception.isFree()) {
            reception.setFree(true);
            PersonDto person = reception.getPersonDto();
            reception.setPersonDto(null);
            reception.setTicketDto(null);
            return "Приём у Person " + person.getName() + " " + person.getSurname()
                    + " окончен.";
        } else {
            return "Приёмная пустая";
        }
    }

    public Reception getReception() {
        return reception;
    }

}
