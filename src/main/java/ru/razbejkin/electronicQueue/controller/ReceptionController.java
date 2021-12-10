package ru.razbejkin.electronicQueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.electronicQueue.model.Reception;
import ru.razbejkin.electronicQueue.service.ManagerService;


@RestController
@RequestMapping("/api/reception")
@RequiredArgsConstructor
public class ReceptionController {

    private final ManagerService managerService;

    @PutMapping("/registration")
    public ResponseEntity registrationVisit(@RequestBody String phoneNumber){
        return ResponseEntity.ok().body(managerService.registrationVisit(phoneNumber));
    }

    @PutMapping("/end-meeting")
    public ResponseEntity endMeeting(){
        return ResponseEntity.ok().body(managerService.endMeeting());
    }

    @GetMapping
    public ResponseEntity<Reception> checkReception(){
        return ResponseEntity.ok().body(managerService.checkReception());
    }


}
