package ru.razbejkin.electronicQueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.razbejkin.electronicQueue.entity.Role;

import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {

    Role findByName(String roleName);

}
