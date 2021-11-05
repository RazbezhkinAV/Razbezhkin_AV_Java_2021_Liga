package ru.razbejkin.electronicQueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.util.Reception;
import ru.razbejkin.electronicQueue.util.MappingDTO;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    private final PersonService personService;
    private final ReceptionService receptionService;
    private final TicketService ticketService;
    private final OnlineTicketService onlineTicketService;
    private final LiveQueueService liveQueueService;

    @Override
    public List<TicketDto> showAllTicket() {
        return onlineTicketService.getAllTicket();
    }

    @Override
    public String callNextInLiveQueue() {
        PersonDto person = liveQueueService.collFirstInQueue();
        if (!receptionService.receptionIsFree())
            return "На данный момент ведётся приём";
        if (person != null) {
            receptionService.startMeeting(person, null);
            return "из очереди приглашён Person " + person.getName();
        } else
            return "Очередь пустая";
    }

    @Override
    public Reception checkReception() {
        return receptionService.getReception();
    }

    @Override
    @Transactional
    public String registrationVisit(String personPhone) {
        Person person = personService.findByPhoneNumber(personPhone);
        if (person == null)
            return "Пользователя с номером телефона " + personPhone
                    + " нет в системе.";

        Ticket ticket = chekPersonTicket(person.getVisitHistory());
        if (ticket == null)
            return "У вас нет онлайн регистрации а ближайшее время";

        if (!receptionService.receptionIsFree())
            return "На данный момент приём ведётся, необходимо подождать.";

        Ticket ticket1 = ticketService.findById(ticket.getId());
        ticket1.setVisit(true);

        PersonDto personDto = MappingDTO.mapToPersonDto(person);
        onlineTicketService.setTicketIsVisit(ticket.getTime());

        return receptionService.startMeeting(personDto, ticket1);
    }

    @Override
    public TicketDto getNearActiveTicket() {
        return onlineTicketService.getNearActiveTicket();
    }

    @Override
    public List<PersonDto> getLiveQueue() {
        return liveQueueService.getPersonQueue();
    }

    @Override
    @Transactional
    public String removeTicket(String time) {
        Ticket ticket = ticketService.findByTime(time);
        if (ticket == null)
            return "Талон на время " + time + " отсутствует";

        if (ticket.isVisit())
            return "Невозможно отменить таллон, тк он уже использован";

        ticketService.delete(ticket);
        onlineTicketService.setTicketIsFree(time);

        return "Талоне отменён";
    }

    @Override
    public String endMeeting() {
        return receptionService.endMeeting();
    }

    /*
    Проверка что билет пользователя находится в пределах
    время записи (-30 минут)
    время записи (+15 минут)
     */

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
