package com.razbejkin.lesson10.dao;

import com.razbejkin.lesson10.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonDAO extends JpaRepository<Person, UUID> {
}
