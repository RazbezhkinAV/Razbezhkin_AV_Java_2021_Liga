package ru.razbejkin.electronicQueue.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.razbejkin.electronicQueue.model.Reception;
import ru.razbejkin.electronicQueue.service.TicketService;

import java.time.LocalTime;

@Configuration
public class MyConfiguration {
    @Bean
    public Reception reception() {
        return new Reception();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner run(TicketService ticketService) {
        return args -> {
            LocalTime localTime = LocalTime.now();
            ticketService.chekActualTime(localTime);
        };
    }

}
