package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.dao.PersonDAO;
import com.razbejkin.lesson10.dto.FriendDTO;
import com.razbejkin.lesson10.dto.PersonDTO;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.util.MappingPersonDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonDAO personDAO;
    private final MappingPersonDTO mappingPersonDTO;

    public Page<PersonDTO> findAllPerson(PageRequest pageable){
        Page<Person> personPage = personDAO.findAll(pageable);
        int totalElement = (int) personPage.getTotalElements();
        return new PageImpl<PersonDTO>(personPage.stream()
                .map(mappingPersonDTO::mapToPersonDTO)
                .collect(Collectors.toList()),pageable,totalElement);
    }

    public void savePerson(Person person){
        personDAO.save(person);
    }

    public PersonDTO findPersonById(String id){
        return mappingPersonDTO.mapToPersonDTO(personDAO.findById(UUID.fromString(id)).get());
    }

    public void deletePerson(String id){
        Person person = personDAO.findById(UUID.fromString(id)).get();
        personDAO.delete(person);
    }

    public void addFriend(String personId,String friendId){
        Person person = personDAO.findById(UUID.fromString(personId)).get();
        Person friend = personDAO.findById(UUID.fromString(friendId)).get();

        person.addFriend(friend);
        friend.addFriend(person);
        personDAO.save(person);
        personDAO.save(friend);
    }

    public void deleteFriend(String personId,String friendId){
        Person person = personDAO.findById(UUID.fromString(personId)).get();
        Person friend = personDAO.findById(UUID.fromString(friendId)).get();

        person.removeFriend(friend);
        friend.removeFriend(person);
        personDAO.save(person);
        personDAO.save(friend);
    }

    public List<FriendDTO> showFriends(String id){
        Person person = personDAO.findById(UUID.fromString(id)).get();
        return person.getFriends().stream()
                .map(mappingPersonDTO::mapToFriendList)
                .collect(Collectors.toList());
    }

}
