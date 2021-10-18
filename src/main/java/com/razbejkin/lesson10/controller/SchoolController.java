package com.razbejkin.lesson10.controller;

import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.entity.School;
import com.razbejkin.lesson10.servise.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson10/school")
@RequiredArgsConstructor
@Api(value = "School Crud operation",description ="School Crud operation" )
public class SchoolController {

    private final SchoolService schoolService;

    @ApiOperation(value = "show all School")
    @GetMapping
    public List<School> showAllSchool(){
        return schoolService.getAllSchool();
    }

    @ApiOperation(value = "save new School")
    @PostMapping
    public void saveSchool(@RequestBody School school){
        schoolService.saveSchool(school);
    }

    @ApiOperation(value = "find School by ID")
    @GetMapping("/{id}")
    public School findSchoolById(@PathVariable("id") String id){
        return schoolService.findSchoolById(id);
    }

    @ApiOperation(value = "delete School by ID")
    @DeleteMapping("/{id}")
    public String deleteSchool(@PathVariable("id") String id){
        schoolService.deleteSchool(id);
        return "School with ID "+id+" was deleted";
    }

    @ApiOperation(value = "updated data School")
    @PutMapping
    public School updateSchool(@RequestBody School school){
        schoolService.saveSchool(school);
        return school;
    }
}
