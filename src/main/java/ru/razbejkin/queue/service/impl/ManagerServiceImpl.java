package ru.razbejkin.queue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.queue.entity.Reception;
import ru.razbejkin.queue.dto.PersonDto;
import ru.razbejkin.queue.entity.Person;
import ru.razbejkin.queue.entity.Ticket;
import ru.razbejkin.queue.repository.PersonRepo;
import ru.razbejkin.queue.repository.TicketRepo;
import ru.razbejkin.queue.service.ManagerService;
import ru.razbejkin.queue.dto.MappingDTO;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    private final ReceptionService receptionService;
    private final TicketRepo ticketRepo;
    private final OnlineTicketService onlineTicketService;
    private final PersonRepo personRepo;

    @Override
    @Transactional
    public String registrationVisit(String personPhone) {
        Person person = personRepo.findByPhoneNumber(personPhone);
        if (person == null)
            return "Пользователя с номером телефона " + personPhone
                    + " нет в системе.";

        Ticket ticket = chekPersonTicket(person.getVisitHistory());
        if (ticket == null)
            return "У вас нет онлайн регистрации а ближайшее время";

        if (!receptionService.receptionIsFree())
            return "На данный момент приём ведётся, необходимо подождать.";

        Ticket ticket1 = ticketRepo.findById(ticket.getId()).orElse(null);
        ticket1.setVisit(true);

        PersonDto personDto = MappingDTO.mapToPersonDto(person);
        onlineTicketService.setTicketIsVisit(ticket.getTime());

        return receptionService.startMeeting(personDto, ticket1);
    }

    @Override
    public String endMeeting() {
        return receptionService.endMeeting();
    }

    @Override
    public Reception checkReception() {
        return receptionService.getReception();
    }

    private Ticket chekPersonTicket(List<Ticket> ticketList) {
        LocalTime currentTime = LocalTime.now();

        for (Ticket ticket :
                ticketList) {

            LocalTime time = LocalTime.parse(ticket.getTime());

            if (currentTime.isAfter(time.minusMinutes(30))
                    && currentTime.isBefore(time.plusMinutes(30)))
                return ticket;
        }
        return null;
    }

}
