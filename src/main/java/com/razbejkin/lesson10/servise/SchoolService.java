package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.dao.SchoolDAO;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolDAO schoolDAO;

    public List<School> getAllSchool(){
        return schoolDAO.findAll();
    }
    public void saveSchool(School school){
        schoolDAO.save(school);
    }

    public School findSchoolById(String id){
        return schoolDAO.findById(UUID.fromString(id)).get();
    }

    public void deleteSchool(String id){
        School school = schoolDAO.findById(UUID.fromString(id)).get();
        schoolDAO.delete(school);
    }

}
