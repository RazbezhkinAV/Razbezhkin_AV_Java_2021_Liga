package ru.razbejkin.electronicQueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.electronicQueue.repository.PersonRepo;
import ru.razbejkin.electronicQueue.repository.RoleRepo;
import ru.razbejkin.electronicQueue.dto.PersonDtoFromAdmin;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;
import ru.razbejkin.electronicQueue.util.MappingDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Person savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepo.save(person);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<PersonDtoFromAdmin> getPersons() {
        return personRepo.findAll().stream()
                .map(MappingDTO::mapToPersonDtoFromAdmin)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addRoleToPerson(String personName, String roleName) {
        Person person = personRepo.findByLogin(personName);
        Role role = roleRepo.findByName(roleName);
        person.setRole(role);
    }


}
