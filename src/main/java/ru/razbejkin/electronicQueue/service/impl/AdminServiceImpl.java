package ru.razbejkin.electronicQueue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;
import ru.razbejkin.electronicQueue.repository.PersonRepo;
import ru.razbejkin.electronicQueue.repository.RoleRepo;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;

    @Override
    @Transactional
    public void saveRole(Role role) {
         roleRepo.save(role);
    }

    @Override
    @Transactional
    public void addRoleToPerson(String loginPerson, String roleName) {
        Person person = personRepo.findByLogin(loginPerson);
        Role role = roleRepo.findByName(roleName);
        person.setRole(role);
    }
}
