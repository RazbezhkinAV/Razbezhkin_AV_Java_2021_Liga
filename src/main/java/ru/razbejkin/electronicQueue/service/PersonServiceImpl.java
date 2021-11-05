package ru.razbejkin.electronicQueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.electronicQueue.repository.PersonRepo;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.util.MappingDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonRepo personRepo;
    private final OnlineTicketService onlineTicketService;
    private final LiveQueueService liveQueueService;
    private final TicketService ticketService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepo.findByLogin(login);
        if (person == null) {
            throw new UsernameNotFoundException("Person not found in database");
        }
        /*
         * Формируем список ролей которые есть у пользователя
         */
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(person.getRole().getName()));

        return new User(person.getLogin(), person.getPassword(), authorities);
    }

    public Person info(){
        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String login = user.getName();
        return personRepo.findByLogin(login);
    }

    @Override
    public PersonDto showMyInfo() {
        Person person = info();
        return MappingDTO.mapToPersonDto(person);
    }

    @Override
    public List<TicketDto> showMyTicket() {
        return info().getVisitHistory().stream()
                .map(MappingDTO::mapToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String onlineRegisterTicket(String time) {
        if (onlineTicketService.ticketIsFree(time)) {
            Person person = info();

            Ticket ticket = new Ticket();
            ticket.setTime(time);
            ticket.setFree(false);

            onlineTicketService.setTicketIsBusy(time, MappingDTO.mapToPersonDto(person));

            person.addTicket(ticket);

            onlineTicketService.chekConfirm(MappingDTO.mapToTicketDto(ticket));
            System.out.println("Пожалуйста подтвердите выбранное время по ссылке в течении 15 минут " +
                    "http://localhost:8080/api/person/ticket/confirm/" + person.getPhoneNumber() + "/" + ticket.getTime());
            return "Person " + person.getName() + " выбрал время на " + ticket.getTime();
        }
        return "Неверное время";
    }

    @Override
    public List<TicketDto> showFreeTicket() {
        return onlineTicketService.getFreeTicket();
    }

    @Override
    public List<PersonDto> showLiveQueue() {
        return liveQueueService.getPersonQueue();
    }

    @Override
    public String getToLine() {
        Person person = info();

        if (liveQueueService.addPersonToQueue(person))
            return "Номер в очереди " + liveQueueService.numberInQueue(person);
        else
            return "В уже стоите в очереди под номером " + liveQueueService.numberInQueue(person);
    }

    @Override
    public String getOutOfQueue() {
        Person person = info();

        liveQueueService.removePersonFromQueue(person);

        return "Person " + person.getName() + " ушёл из очереди";
    }

    @Override
    public Person findByPhoneNumber(String phoneNumber) {
        return personRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public String confirmTicket(String phone, String time) {
        Ticket ticket = ticketService.findByTime(time);
        ticket.setConfirm(true);
        onlineTicketService.setTicketIsConfirm(ticket.getTime());

        if (ticket.isConfirm())
            return "Заявка подтверждена";
        else
            return "Заявка не подтверждена";
    }
}
