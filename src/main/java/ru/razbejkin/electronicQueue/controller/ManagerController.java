package ru.razbejkin.electronicQueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.model.Reception;
import ru.razbejkin.electronicQueue.service.ManagerService;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/tickets")
    public List<TicketDto> showAllTicket() {
        return managerService.showAllTicket();
    }

    @GetMapping("/ticket/nearActive")
    public TicketDto getNearActiveTicket() {
        return managerService.getNearActiveTicket();
    }

    @DeleteMapping("/ticket/remove")
    public String removeTicket(@RequestBody String time) {
        return managerService.removeTicket(time);
    }

    @GetMapping("/liveQueue")
    public List<PersonDto> showLiveQueue() {
        return managerService.getLiveQueue();
    }

    @GetMapping("/liveQueue/next")
    public String callNextInLiveQueue() {
        return managerService.callNextInLiveQueue();
    }

    @GetMapping("/reception/status")
    public Reception chekStatusReception() {
        return managerService.checkReception();
    }

    @PostMapping("/reception/registration")
    public String registrationVisitPerson(@RequestBody String phoneNumber) {
        return managerService.registrationVisit(phoneNumber);
    }

    @GetMapping("/reception/endMeeting")
    public String endMeeting() {
        return managerService.endMeeting();
    }


}
