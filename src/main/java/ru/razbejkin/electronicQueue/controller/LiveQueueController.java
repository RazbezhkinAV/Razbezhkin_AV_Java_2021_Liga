package ru.razbejkin.electronicQueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.service.impl.LiveQueueService;

import java.util.List;

@RestController
@RequestMapping("/api/live-queue")
@RequiredArgsConstructor
public class LiveQueueController {

    private final LiveQueueService liveQueueService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> showLiveQueue(){
       return ResponseEntity.ok().body(liveQueueService.showLiveQueue());
    }

    @PutMapping("/next-person")
    public ResponseEntity callNextPerson(){
        return ResponseEntity.ok().body(liveQueueService.callNextInLiveQueue());
    }

    @PostMapping("/get-to-line")
    public ResponseEntity getToLine(){
        return ResponseEntity.ok().body(liveQueueService.getToLine());
    }

    @DeleteMapping("/get-out")
    public ResponseEntity getOutOfLiveQueue(){
        return ResponseEntity.ok().body(liveQueueService.getOutOfQueue());
    }
}
