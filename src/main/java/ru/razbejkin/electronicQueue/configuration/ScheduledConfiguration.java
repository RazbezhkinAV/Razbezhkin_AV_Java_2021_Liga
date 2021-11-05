package ru.razbejkin.electronicQueue.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.razbejkin.electronicQueue.service.OnlineTicketService;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
class ScheduledConfiguration {

    private final LocalTime currentTime = LocalTime.now();
    private final OnlineTicketService onlineTicketService;

    @Scheduled(cron = "* 0 9-21 * * *")
    public void checkActualTicket() {
        onlineTicketService.chekActualTime(currentTime);
    }

    @Scheduled(cron = "* 15 9-21 * * *")
    public void lateForVisit() {
        onlineTicketService.lateForVisit(currentTime);
    }

}
