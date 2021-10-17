package com.razbejkin.lesson10.controller;

import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.entity.School;
import com.razbejkin.lesson10.servise.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/")
    public List<School> showAllSchool(){
        return schoolService.getAllSchool();
    }

    @PostMapping("/")
    public void savePerson(@RequestBody School school){
        schoolService.saveSchool(school);
    }

    @GetMapping("/{id}")
    public School findSchoolById(@PathVariable("id") String id){
        return schoolService.findSchoolById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSchool(@PathVariable("id") String id){
        schoolService.deleteSchool(id);
        return "School with ID "+id+" was deleted";
    }

    @PutMapping("/")
    public School updatePerson(@RequestBody School school){
        schoolService.saveSchool(school);
        return school;
    }
}
