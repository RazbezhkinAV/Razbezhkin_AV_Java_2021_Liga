package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.dao.PersonDAO;
import com.razbejkin.lesson10.dto.FriendDTO;
import com.razbejkin.lesson10.dto.PersonDTO;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.util.MappingPersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonDAO personDAO;
    private final MappingPersonDTO mappingPersonDTO;

    public List<PersonDTO> findAllPerson(){
        return personDAO.findAll().stream()
                .map(mappingPersonDTO::mapToPersonDTO)
                .collect(Collectors.toList());
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
