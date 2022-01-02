package ru.razbejkin.queue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.razbejkin.queue.dto.PersonDto;
import ru.razbejkin.queue.dto.TicketDto;
import ru.razbejkin.queue.entity.Person;
import ru.razbejkin.queue.entity.Ticket;
import ru.razbejkin.queue.service.PersonService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<PersonDto> showMyInfo() {
        return ResponseEntity.ok().body(personService.showMyInfo());
    }

    @PostMapping()
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return ResponseEntity.ok().body(personService.savePerson(person));
    }

    @PutMapping()
    public ResponseEntity<Person> update(@RequestBody Person person){
        return ResponseEntity.ok().body(personService.updatePerson(person));
    }

    @DeleteMapping
    public void delete(){
        personService.deletePerson();
    }

    @GetMapping("/my-ticket")
    public ResponseEntity<List<TicketDto>> getMyTicket() {
        return ResponseEntity.ok().body(personService.showMyTicket());
    }

    @PostMapping("/registration-ticket")
    public ResponseEntity registrationTicket(@RequestBody String time) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/person/registration-ticket").toUriString());
            System.out.println("uri" + uri);
            Ticket ticket = personService.onlineRegisterTicket(time);
            URI approveUri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
                    "api/ticket/confirm/" + ticket.getId()).toUriString());
            return ResponseEntity.ok().body(approveUri);
    }
}
