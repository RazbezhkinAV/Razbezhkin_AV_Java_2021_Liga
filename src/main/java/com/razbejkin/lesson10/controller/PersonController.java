package com.razbejkin.lesson10.controller;

import com.razbejkin.lesson10.dto.FriendDTO;
import com.razbejkin.lesson10.dto.PersonDTO;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.servise.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Page<PersonDTO> findAllPerson(@RequestParam Optional<Integer> page,
                                      @RequestParam Optional<Integer> size,
                                      @RequestParam Optional<String> sortBy){
        return personService.findAllPerson(PageRequest.of(
                page.orElse(0),
                size.orElse(5),
                Sort.Direction.ASC,sortBy.orElse("id")));
    }

    @PostMapping
    public void savePerson(@RequestBody Person person){
        personService.savePerson(person);
    }

    @GetMapping("/{id}")
    public PersonDTO findPerson(@PathVariable("id") String id){
       return personService.findPersonById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") String id){
        personService.deletePerson(id);
        return "Person with ID "+id+" was deleted";
    }

    @PutMapping
    public String updatePerson(@RequestBody Person person){
        personService.savePerson(person);
        return "Updated person data";
    }

    @PostMapping("/friends/{id}")
    public String addFriend(@PathVariable("id") String personId,@RequestBody String friendId){
        personService.addFriend(personId, friendId);
        return "add friend";
    }

    @GetMapping("/friends/{id}")
    public List<FriendDTO> showFriends(@PathVariable("id") String id){
        return personService.showFriends(id);
    }

    @DeleteMapping("/friends/{id}")
    public String deleteFriend(@PathVariable("id") String personId,@RequestBody String friendId){
        personService.deleteFriend(personId, friendId);
        return "delete friend";
    }

}
