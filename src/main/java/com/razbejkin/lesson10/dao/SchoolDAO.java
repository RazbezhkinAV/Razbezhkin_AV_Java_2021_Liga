package com.razbejkin.lesson10.dao;

import com.razbejkin.lesson10.entity.School;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchoolDAO extends JpaRepository<School, UUID> {

}
