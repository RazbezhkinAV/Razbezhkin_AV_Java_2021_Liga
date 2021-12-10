package ru.razbejkin.electronicQueue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.exception.TicketBusyException;
import ru.razbejkin.electronicQueue.repository.PersonRepo;
import ru.razbejkin.electronicQueue.repository.RoleRepo;
import ru.razbejkin.electronicQueue.service.PersonService;
import ru.razbejkin.electronicQueue.service.TicketService;
import ru.razbejkin.electronicQueue.controller.model.RequestCreateNewPerson;
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
    private final TicketService ticketService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personRepo.findByLogin(login);
        if (person == null) {
            throw new UsernameNotFoundException("Person not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(person.getRole().getName()));
        return new User(person.getLogin(), person.getPassword(), authorities);
    }

    public Person info() {
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
    @Transactional
    public PersonDto savePerson(RequestCreateNewPerson newPerson) {
        Person person = new Person();
        person.setLogin(newPerson.getLogin());
        person.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        person.setName(newPerson.getName());
        person.setSurname(newPerson.getSurname());
        person.setEmail(newPerson.getEmail());
        person.setPhoneNumber(newPerson.getPhoneNumber());
        person.setRole(roleRepo.findByName("ROLE_PERSON"));
        return MappingDTO.mapToPersonDto(personRepo.save(person));
    }

    @Override
    public List<TicketDto> showMyTicket() {
        return info().getVisitHistory().stream()
                .map(MappingDTO::mapToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Ticket onlineRegisterTicket(String time) throws TicketBusyException {
        if (!onlineTicketService.ticketIsFree(time)) {
            throw new TicketBusyException();
        }

        Person person = info();
        Ticket ticket = new Ticket();
        ticket.setTime(time);
        ticket.setFree(false);

        onlineTicketService.setTicketIsBusy(time, MappingDTO.mapToPersonDto(person));

        person.addTicket(ticket);

        ticketService.chekConfirm(MappingDTO.mapToTicketDto(ticket));
        return ticket;
    }

    @Override
    public boolean checkPersonByLogin(String login) {
        return personRepo.findByLogin(login) != null ? true : false;
    }
}
