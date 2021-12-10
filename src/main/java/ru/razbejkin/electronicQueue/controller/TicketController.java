package ru.razbejkin.electronicQueue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.electronicQueue.dto.TicketDto;
import ru.razbejkin.electronicQueue.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketDto>> getAllTicket(){
        return ResponseEntity.ok().body(ticketService.showAllTicket());
    }

    @GetMapping("/free-tickets")
    public ResponseEntity<List<TicketDto>> getAllFreeTicket(){
        return ResponseEntity.ok().body(ticketService.showFreeTicket());
    }

    @GetMapping ("/near-active-ticket")
    public ResponseEntity<TicketDto> getNearActiveTicket(){
        return ResponseEntity.ok().body(ticketService.getNearActiveTicket());
    }

    @DeleteMapping
    public ResponseEntity removeTicket(@RequestBody String time){
        return ResponseEntity.ok().body(ticketService.removeTicket(time));
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity confirmTicket(@PathVariable ("id") String id){
        return ResponseEntity.ok().body(ticketService.confirmTicket(id));
    }

}
