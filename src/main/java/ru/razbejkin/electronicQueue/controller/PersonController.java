package ru.razbejkin.electronicQueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/info")
    public PersonDto showMyInfo() {
        return personService.showMyInfo();
    }

    @GetMapping("/my-ticket")
    public List<TicketDto> showMyTicket() {
        return personService.showMyTicket();
    }

    @PostMapping("/online-registration")
    public String onlineRegistrationTicket(@RequestBody String time) {
        return personService.onlineRegisterTicket(time);
    }

    @GetMapping("/free-tickets")
    public List<TicketDto> freeTickets() {
        return personService.showFreeTicket();
    }

    @GetMapping("/live-queue")
    public List<PersonDto> showLiveQueue() {
        return personService.showLiveQueue();
    }

    @GetMapping("/live-queue/get-to-line")
    public String getToLine() {
        return personService.getToLine();
    }

    @GetMapping("/liveQueue/get-out")
    public String getOutOfQueue(HttpServletRequest request) {
        return personService.getOutOfQueue();
    }

    @GetMapping("/ticket/confirm/{phone}/{time}")
    public String confirmTicket(@PathVariable("phone") String phone, @PathVariable("time") String time) {
        return personService.confirmTicket(phone, time);
    }
}

