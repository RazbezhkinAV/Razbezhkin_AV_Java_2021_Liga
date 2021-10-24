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
    public PersonDto showMyInfo(HttpServletRequest request) {
        return personService.showMyInfo(request);
    }

    @GetMapping("/myTicket")
    public List<TicketDto> showMyTicket(HttpServletRequest request) {
        return personService.showMyTicket(request);
    }

    @PostMapping("/onlineRegistration")
    public String onlineRegistrationTicket(HttpServletRequest request, @RequestBody String time) {
        return personService.onlineRegisterTicket(request, time);
    }

    @GetMapping("/freeTickets")
    public List<TicketDto> freeTickets() {
        return personService.showFreeTicket();
    }

    @GetMapping("/liveQueue")
    public List<PersonDto> showLiveQueue() {
        return personService.showLiveQueue();
    }

    @GetMapping("/liveQueue/getToLine")
    public String getToLine(HttpServletRequest request) {
        return personService.getToLine(request);
    }

    @GetMapping("/liveQueue/getOut")
    public String getOutOfQueue(HttpServletRequest request) {
        return personService.getOutOfQueue(request);
    }

    @GetMapping("/ticket/confirm/{phone}/{time}")
    public String confirmTicket(@PathVariable("phone") String phone, @PathVariable("time") String time) {
        return personService.confirmTicket(phone, time);
    }
}

