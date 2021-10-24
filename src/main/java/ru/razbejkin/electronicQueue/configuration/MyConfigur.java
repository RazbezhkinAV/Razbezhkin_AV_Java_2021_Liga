package ru.razbejkin.electronicQueue.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;
import ru.razbejkin.electronicQueue.model.OnlineTicketService;
import ru.razbejkin.electronicQueue.model.Reception;
import ru.razbejkin.electronicQueue.service.AdminService;

import java.time.LocalTime;

@Configuration
public class MyConfigur {
    @Bean
    public Reception reception() {
        return new Reception();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner run(AdminService adminService, OnlineTicketService onlineTicketService) {
        return args -> {
            adminService.saveRole(new Role(null, "ROLE_ADMIN"));
            adminService.saveRole(new Role(null, "ROLE_MANAGER"));
            adminService.saveRole(new Role(null, "ROLE_PERSON"));

            adminService.savePerson(new Person("admin", "1234", "Adomin", null, "911", null, null));
            adminService.savePerson(new Person("sasha01", "1234", "Sasha", "Razbejkin", "89996690413", "sasha93@gmail.com", null));
            adminService.savePerson(new Person("dasha02", "1234", "Dasha", "Perova", "89665421325", "dasha93@gmail.com", null));
            adminService.savePerson(new Person("misha03", "1234", "Misha", "Ivanov", "89632584125", "misha93@gmail.com", null));

            adminService.addRoleToPerson("admin", "ROLE_ADMIN");
            adminService.addRoleToPerson("sasha01", "ROLE_PERSON");
            adminService.addRoleToPerson("dasha02", "ROLE_PERSON");
            adminService.addRoleToPerson("misha03", "ROLE_MANAGER");

            LocalTime localTime = LocalTime.now();
            onlineTicketService.chekActualTime(localTime);
        };
    }

}
