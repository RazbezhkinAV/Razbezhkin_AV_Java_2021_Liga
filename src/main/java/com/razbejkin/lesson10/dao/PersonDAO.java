package com.razbejkin.lesson10.dao;

import com.razbejkin.lesson10.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PersonDAO extends JpaRepository<Person, UUID> {
}
