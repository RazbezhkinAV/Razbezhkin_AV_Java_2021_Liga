package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.dto.PersonDtoFromAdmin;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;

import java.util.List;

public interface AdminService {

    Person savePerson(Person person);

    Role saveRole(Role role);

    List<PersonDtoFromAdmin> getPersons();

    void addRoleToPerson(String personName, String roleName);
}
