package ru.razbejkin.electronicQueue.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.dao.PersonDao;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.model.LiveQueueService;
import ru.razbejkin.electronicQueue.util.MappingDTO;
import ru.razbejkin.electronicQueue.model.OnlineTicketService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonDao personDao;
    private final OnlineTicketService onlineTicketService;
    private final LiveQueueService liveQueueService;
    private final TicketService ticketService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personDao.findByLogin(login);
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

    public Person myInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("oneliner".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String login = decodedJWT.getSubject();
        return personDao.findByLogin(login);
    }

    @Override
    public PersonDto showMyInfo(HttpServletRequest request) {
        Person person = myInfo(request);
        return MappingDTO.mapToPersonDto(person);
    }

    @Override
    public List<TicketDto> showMyTicket(HttpServletRequest request) {
        return myInfo(request).getVisitHistory().stream()
                .map(MappingDTO::mapToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    public String onlineRegisterTicket(HttpServletRequest request, String time) {
        if (onlineTicketService.ticketIsFree(time)) {
            Person person = myInfo(request);

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
    public String getToLine(HttpServletRequest request) {
        Person person = myInfo(request);

        if (liveQueueService.addPersonToQueue(person))
            return "Номер в очереди " + liveQueueService.numberInQueue(person);
        else
            return "В уже стоите в очереди под номером " + liveQueueService.numberInQueue(person);
    }

    @Override
    public String getOutOfQueue(HttpServletRequest request) {
        Person person = myInfo(request);

        liveQueueService.removePersonFromQueue(person);

        return "Person " + person.getName() + " ушёл из очереди";
    }

    @Override
    public Person findByPhoneNumber(String phoneNumber) {
        return personDao.findByPhoneNumber(phoneNumber);
    }

    @Override
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
