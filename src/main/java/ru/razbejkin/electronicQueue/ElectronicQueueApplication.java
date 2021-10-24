package ru.razbejkin.electronicQueue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;
import ru.razbejkin.electronicQueue.service.AdminService;

@SpringBootApplication
@EnableScheduling
public class ElectronicQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicQueueApplication.class, args);
    }


}
