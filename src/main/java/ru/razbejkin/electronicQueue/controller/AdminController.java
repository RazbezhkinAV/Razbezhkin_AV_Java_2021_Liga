package ru.razbejkin.electronicQueue.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.razbejkin.electronicQueue.dto.PersonDtoFromAdmin;
import ru.razbejkin.electronicQueue.entity.Person;
import ru.razbejkin.electronicQueue.entity.Role;
import ru.razbejkin.electronicQueue.service.AdminService;
import ru.razbejkin.electronicQueue.util.RoleToUserForm;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save/person")
    public Person savePerson(@RequestBody Person person) {
        return adminService.savePerson(person);
    }

    @PostMapping("/save/role")
    public Role saveRole(@RequestBody Role role) {
        return adminService.saveRole(role);
    }

    @GetMapping("/persons")
    public List<PersonDtoFromAdmin> getPersons() {
        return adminService.getPersons();
    }

    @PostMapping("/role/add-role-to-person")
    public void addRoleToPerson(@RequestBody RoleToUserForm role) {
        adminService.addRoleToPerson(role.getPersonName(), role.getRoleName());
    }
}

