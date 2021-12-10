package ru.razbejkin.electronicQueue.controller.version2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.razbejkin.electronicQueue.controller.version2.model.ResponseCreatePerson;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.entity.Ticket;
import ru.razbejkin.electronicQueue.exception.TicketBusyException;
import ru.razbejkin.electronicQueue.service.PersonService;
import ru.razbejkin.electronicQueue.controller.version2.model.RequestCreateNewPerson;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<PersonDto> showMyInfo() {
        return ResponseEntity.ok().body(personService.showMyInfo());
    }

    @PostMapping("/registration")
    public ResponseEntity<ResponseCreatePerson> savePerson(@RequestBody RequestCreateNewPerson newPerson) {
        try {
            if (!personService.checkPersonByLogin(newPerson.getLogin())) {
                PersonDto personDto = personService.savePerson(newPerson);
                String message = "Вы зарегистрированы";
                return ResponseEntity.ok().body(new ResponseCreatePerson(message, personDto));
            } else {
                String message = "Данный логин уже существует";
                return ResponseEntity.badRequest().body(new ResponseCreatePerson(message, null));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseCreatePerson(e.getMessage(), null));
        }
    }

    @GetMapping("/my-ticket")
    public ResponseEntity<List<TicketDto>> getMyTicket() {
        return ResponseEntity.ok().body(personService.showMyTicket());
    }

    @PostMapping("/registration-ticket")
    public ResponseEntity registrationTicket(@RequestBody String time) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/person/registration-ticket").toUriString());
            System.out.println("uri" + uri);
            Ticket ticket = personService.onlineRegisterTicket(time);
            URI approveUri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
                    "api/ticket/confirm/" + ticket.getId()).toUriString());
            return ResponseEntity.ok().body(approveUri);
        } catch (TicketBusyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
