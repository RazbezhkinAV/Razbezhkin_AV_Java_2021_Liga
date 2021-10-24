package ru.razbejkin.electronicQueue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.razbejkin.electronicQueue.entity.Person;

import java.util.UUID;

@Repository
public interface PersonDao extends JpaRepository<Person, UUID> {

    Person findByLogin(String login);

    Person findByPhoneNumber(String phoneNumber);
}
