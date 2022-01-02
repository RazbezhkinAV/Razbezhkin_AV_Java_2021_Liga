package ru.razbejkin.queue.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PersonDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private List<TicketDto> ticketDtoList;
}
