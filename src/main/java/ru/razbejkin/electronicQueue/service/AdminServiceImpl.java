package ru.razbejkin.electronicQueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.dao.PersonDao;
import ru.razbejkin.electronicQueue.dao.RoleDao;
import ru.razbejkin.electronicQueue.dto.PersonDtoFromAdmin;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;
import ru.razbejkin.electronicQueue.util.MappingDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final PersonDao personDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Person savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personDao.save(person);
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public List<PersonDtoFromAdmin> getPersons() {
        return personDao.findAll().stream()
                .map(MappingDTO::mapToPersonDtoFromAdmin)
                .collect(Collectors.toList());
    }

    @Override
    public void addRoleToPerson(String personName, String roleName) {
        Person person = personDao.findByLogin(personName);
        Role role = roleDao.findByName(roleName);
        person.setRole(role);
    }


}
