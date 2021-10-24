package ru.razbejkin.electronicQueue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.razbejkin.electronicQueue.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Role findByName(String roleName);

}
