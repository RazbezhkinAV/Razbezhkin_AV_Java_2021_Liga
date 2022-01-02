package ru.razbejkin.queue.service.impl;

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
import ru.razbejkin.queue.dto.PersonDto;
import ru.razbejkin.queue.dto.TicketDto;
import ru.razbejkin.queue.entity.Person;
import ru.razbejkin.queue.entity.Ticket;
import ru.razbejkin.queue.exception.PersonException;
import ru.razbejkin.queue.repository.PersonRepo;
import ru.razbejkin.queue.repository.RoleRepo;
import ru.razbejkin.queue.service.PersonService;
import ru.razbejkin.queue.service.TicketService;
import ru.razbejkin.queue.dto.MappingDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonRepo personRepo;
    private final OnlineTicketService onlineTicketService;
    private final TicketService ticketService;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

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

    private Person info() {
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
    public Person savePerson(Person person) {

        Optional<Person> personOptional = personRepo.findPersonByLogin(person.getLogin());

        if (personOptional.isPresent())
            throw new PersonException("Login is busy");

        person.setRole(roleRepo.findByName("ROLE_PERSON"));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepo.save(person);
    }

    @Override
    public Person updatePerson(Person updatePerson) {
        Person person = info();

        if (updatePerson.getLogin() != null &&
                updatePerson.getLogin().length() > 0 &&
                updatePerson.getLogin() != person.getLogin()) {
            Optional<Person> optionalPerson = personRepo.findPersonByLogin(updatePerson.getLogin());
            if (optionalPerson.isPresent())
                throw new PersonException("Login = " + updatePerson.getLogin() + " is busy!");
            person.setLogin(updatePerson.getLogin());
        }

        if (updatePerson.getPassword() != null &&
                updatePerson.getPassword().length() > 0 &&
                updatePerson.getPassword() != person.getPassword())
            person.setPassword(updatePerson.getPassword());

        if (updatePerson.getName() != null &&
                updatePerson.getName().length() > 0 &&
                updatePerson.getName() != person.getName())
            person.setName(updatePerson.getName());

        if (updatePerson.getSurname() != null &&
                updatePerson.getSurname().length() > 0 &&
                updatePerson.getSurname() != person.getSurname())
            person.setSurname(updatePerson.getSurname());

        if (updatePerson.getPhoneNumber() != null &&
                updatePerson.getPhoneNumber().length() > 0 &&
                updatePerson.getPhoneNumber() != person.getPhoneNumber()) {
            Optional<Person> optionalPerson = personRepo.findPersonByPhoneNumber(updatePerson.getPhoneNumber());
            if (optionalPerson.isPresent())
                throw new PersonException("Phone " + updatePerson.getPhoneNumber() + " is busy");
            person.setPhoneNumber(updatePerson.getPhoneNumber());
        }

        if (updatePerson.getEmail() != null &&
                updatePerson.getEmail().length() > 0 &&
                updatePerson.getEmail() != person.getEmail())
            person.setEmail(updatePerson.getEmail());

        return person;
    }

    @Override
    public void deletePerson(){
        personRepo.delete(info());
    }

    @Override
    public List<TicketDto> showMyTicket() {
        return info().getVisitHistory().stream()
                .map(MappingDTO::mapToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Ticket onlineRegisterTicket(String time) {
        Person person = info();

        Ticket ticket = new Ticket();
        ticket.setTime(time);
        ticket.setFree(false);

        onlineTicketService.setTicketIsBusy(time, MappingDTO.mapToPersonDto(person));

        person.addTicket(ticket);

        ticketService.chekConfirm(MappingDTO.mapToTicketDto(ticket));
        return ticket;
    }
}
