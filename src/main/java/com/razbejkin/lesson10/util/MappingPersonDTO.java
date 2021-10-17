package com.razbejkin.lesson10.util;

import com.razbejkin.lesson10.dto.FriendDTO;
import com.razbejkin.lesson10.dto.PersonDTO;
import com.razbejkin.lesson10.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MappingPersonDTO {

    private final MappingPostDTO mappingPostDTO;

    public PersonDTO mapToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setSchool(person.getSchool());
        personDTO.setPosts(person.getPosts().stream()
                .map(mappingPostDTO::mapToPostDTO)
                .collect(Collectors.toList()));
        personDTO.setFriends(person.getFriends().stream()
                .map(this::mapToFriendList)
                .collect(Collectors.toList()));
        return personDTO;
    }

    public FriendDTO mapToFriendList(Person person) {
        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setName(person.getSurname() + " " + person.getName());
        return friendDTO;
    }
}
