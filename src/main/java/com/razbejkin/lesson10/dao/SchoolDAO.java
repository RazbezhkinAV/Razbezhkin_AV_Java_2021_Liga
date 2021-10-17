package com.razbejkin.lesson10.dao;

import com.razbejkin.lesson10.entity.School;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface SchoolDAO extends JpaRepository<School, UUID> {

}
