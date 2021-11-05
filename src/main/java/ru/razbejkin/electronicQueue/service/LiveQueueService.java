package ru.razbejkin.electronicQueue.service;

import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.util.MappingDTO;

import java.util.LinkedList;
import java.util.List;

@Service
public class LiveQueueService {

    public LiveQueueService() {
        personList = new LinkedList<>();
    }

    private List<PersonDto> personList;

    public List<PersonDto> getPersonQueue() {
        return personList;
    }

    public boolean addPersonToQueue(Person person) {
        PersonDto personDto = MappingDTO.mapToPersonDto(person);
        if (!personList.contains(personDto))
            return personList.add(personDto);
        else
            return false;
    }

    public void removePersonFromQueue(Person person) {
        PersonDto personDto = MappingDTO.mapToPersonDto(person);
        personList.remove(personDto);
    }

    public int numberInQueue(Person person) {
        PersonDto personDto = MappingDTO.mapToPersonDto(person);
        return personList.indexOf(personDto) + 1;
    }

    public PersonDto collFirstInQueue() {
        if (!personList.isEmpty()) {
            PersonDto person = personList.get(0);
            personList.remove(0);
            return person;
        }
        return null;
    }
}
