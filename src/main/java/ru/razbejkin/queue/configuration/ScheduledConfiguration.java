package ru.razbejkin.queue.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.razbejkin.queue.service.TicketService;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
class ScheduledConfiguration {

    private final LocalTime currentTime = LocalTime.now();
    private final TicketService ticketService;

    @Scheduled(cron = "* 0 9-18 * * *")
    public void checkActualTicket() {
        ticketService.chekActualTime(currentTime);
    }

    @Scheduled(cron = "* 15 9-18 * * *")
    public void lateForVisit() {
        ticketService.lateForVisit(currentTime);
    }

}
