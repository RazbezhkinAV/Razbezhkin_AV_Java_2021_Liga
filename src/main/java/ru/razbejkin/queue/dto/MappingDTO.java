package ru.razbejkin.queue.dto;

import ru.razbejkin.queue.entity.Person;
import ru.razbejkin.queue.entity.Ticket;

import java.util.stream.Collectors;

public class MappingDTO {

    public static PersonDto mapToPersonDto(Person person) {
        PersonDto personDTO = new PersonDto();
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        personDTO.setEmail(person.getEmail());
        personDTO.setTicketDtoList(person.getVisitHistory().stream()
                .map(MappingDTO::mapToTicketDto)
                .collect(Collectors.toList()));
        return personDTO;
    }

    public static TicketDto mapToTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTime(ticket.getTime());
        ticketDto.setPerson_phone(ticket.getPerson().getPhoneNumber());
        ticketDto.setFree(ticket.isFree());
        ticketDto.setVisit(ticket.isVisit());
        ticketDto.setConfirm(ticket.isConfirm());
        return ticketDto;
    }
}
