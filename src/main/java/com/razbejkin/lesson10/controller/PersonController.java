package com.razbejkin.lesson10.controller;

import com.razbejkin.lesson10.dto.FriendDto;
import com.razbejkin.lesson10.dto.PersonDto;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.servise.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lesson10/person")
@RequiredArgsConstructor
@Api(value = "Person Crud operation",description ="Person Crud operation" )
public class PersonController {

    private final PersonService personService;

    @ApiOperation(value = "find all person")
    @GetMapping
    public Page<PersonDto> findAllPerson(@RequestParam Optional<Integer> page,
                                         @RequestParam Optional<Integer> size,
                                         @RequestParam Optional<String> sortBy){
        return personService.findAllPerson(PageRequest.of(
                page.orElse(0),
                size.orElse(5),
                Sort.Direction.ASC,sortBy.orElse("id")));
    }

    @ApiOperation(value = "save person")
    @PostMapping
    public void savePerson(@RequestBody Person person){
        personService.savePerson(person);
    }

    @ApiOperation(value = "find person by ID")
    @GetMapping("/{id}")
    public PersonDto findPerson(@PathVariable("id") String id){
       return personService.findPersonById(id);
    }

    @ApiOperation(value = "delete person be ID")
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") String id){
        personService.deletePerson(id);
    }

    @ApiOperation(value = "updated person data")
    @PutMapping
    public void updatePerson(@RequestBody Person person){
        personService.savePerson(person);
    }

    @ApiOperation(value = "add friend")
    @PostMapping("/friends/{id}")
    public void addFriend(@PathVariable("id") String personId,@RequestBody String friendId){
        personService.addFriend(personId, friendId);
    }

    @ApiOperation(value = "show all friend person")
    @GetMapping("/friends/{id}")
    public List<FriendDto> showFriends(@PathVariable("id") String id){
        return personService.showFriends(id);
    }

    @ApiOperation(value = "delete friend person")
    @DeleteMapping("/friends/{id}")
    public String deleteFriend(@PathVariable("id") String personId,@RequestBody String friendId){
        personService.deleteFriend(personId, friendId);
        return "delete friend";
    }

}
