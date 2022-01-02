package ru.razbejkin.queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ElectronicQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicQueueApplication.class, args);
    }


}
