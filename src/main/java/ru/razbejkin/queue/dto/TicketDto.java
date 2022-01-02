package ru.razbejkin.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private String time;
    private String person_phone;
    private boolean free;
    private boolean visit;
    private boolean confirm;

}
