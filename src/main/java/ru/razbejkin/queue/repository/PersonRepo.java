package ru.razbejkin.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.razbejkin.queue.entity.Person;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepo extends JpaRepository<Person, UUID> {

    Person findByLogin(String login);

    Person findByPhoneNumber(String phoneNumber);

    Optional<Person> findPersonByLogin(String login);

    Optional<Person> findPersonByPhoneNumber(String phone);
}
