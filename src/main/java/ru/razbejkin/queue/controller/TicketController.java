package ru.razbejkin.queue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.queue.dto.TicketDto;
import ru.razbejkin.queue.entity.Ticket;
import ru.razbejkin.queue.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTicket(){
        return ResponseEntity.ok().body(ticketService.getAll());
    }

    @GetMapping("/free")
    public ResponseEntity<List<TicketDto>> getAllFreeTicket(){
        return ResponseEntity.ok().body(ticketService.showFreeTicket());
    }

    @GetMapping ("/near-active")
    public ResponseEntity<TicketDto> getNearActiveTicket(){
        return ResponseEntity.ok().body(ticketService.getNearActiveTicket());
    }

    @DeleteMapping
    public void removeTicket(@RequestBody String time){
        ticketService.removeTicket(time);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<TicketDto> confirmTicket(@PathVariable ("id") String id){
        return ResponseEntity.ok().body(ticketService.confirmTicket(id));
    }

    @GetMapping("{id}")
    public Ticket getTicket(@PathVariable("id") String id){
        return ticketService.getTicket(id);
    }

}
