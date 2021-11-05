package com.razbejkin.lesson10.util;

import com.razbejkin.lesson10.dto.FriendDto;
import com.razbejkin.lesson10.dto.PersonDto;
import com.razbejkin.lesson10.entity.Person;

import java.util.stream.Collectors;

public class MappingPersonDTO {

    public static PersonDto mapToPersonDTO(Person person) {
        PersonDto personDTO = new PersonDto();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setSchool(person.getSchool());
        personDTO.setPosts(person.getPosts().stream()
                .map(MappingPostDTO::mapToPostDTO)
                .collect(Collectors.toList()));
        personDTO.setFriends(person.getFriends().stream()
                .map(MappingPersonDTO::mapToFriendList)
                .collect(Collectors.toList()));
        return personDTO;
    }

    public static FriendDto mapToFriendList(Person person) {
        FriendDto friendDTO = new FriendDto();
        friendDTO.setName(person.getSurname() + " " + person.getName());
        return friendDTO;
    }
}
