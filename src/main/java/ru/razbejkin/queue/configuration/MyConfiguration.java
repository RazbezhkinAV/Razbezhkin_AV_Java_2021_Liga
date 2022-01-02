package ru.razbejkin.queue.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.razbejkin.queue.entity.Reception;
import ru.razbejkin.queue.entity.Role;
import ru.razbejkin.queue.repository.RoleRepo;
import ru.razbejkin.queue.service.TicketService;

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
    public CommandLineRunner run(TicketService ticketService, RoleRepo roleRepo) {
        return args -> {
            LocalTime localTime = LocalTime.now();
            ticketService.chekActualTime(localTime);

            roleRepo.save(new Role("ROLE_PERSON"));
            roleRepo.save(new Role("ROLE_MANAGER"));
        };
    }

}
