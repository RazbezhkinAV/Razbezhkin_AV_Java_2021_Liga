package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.repo.SchoolRepo;
import com.razbejkin.lesson10.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepo schoolRepo;

    public List<School> getAllSchool(){
        return schoolRepo.findAll();
    }
    public void saveSchool(School school){
        schoolRepo.save(school);
    }

    public School findSchoolById(String id){
        return schoolRepo.findById(UUID.fromString(id)).get();
    }

    public void deleteSchool(String id){
        School school = schoolRepo.findById(UUID.fromString(id)).get();
        schoolRepo.delete(school);
    }

}
