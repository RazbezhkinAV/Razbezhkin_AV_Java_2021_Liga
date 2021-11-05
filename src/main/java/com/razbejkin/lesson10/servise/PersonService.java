package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.repo.PersonRepo;
import com.razbejkin.lesson10.dto.FriendDto;
import com.razbejkin.lesson10.dto.PersonDto;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.util.MappingPersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;

    public Page<PersonDto> findAllPerson(PageRequest pageable) {
        Page<Person> personPage = personRepo.findAll(pageable);
        int totalElement = (int) personPage.getTotalElements();
        return new PageImpl<PersonDto>(personPage.stream()
                .map(MappingPersonDTO::mapToPersonDTO)
                .collect(Collectors.toList()), pageable, totalElement);
    }

    public void savePerson(Person person) {
        personRepo.save(person);
    }

    public PersonDto findPersonById(String id) {
        return MappingPersonDTO.mapToPersonDTO(personRepo.findById(UUID.fromString(id)).get());
    }

    public void deletePerson(String id) {
        Person person = personRepo.findById(UUID.fromString(id)).get();
        personRepo.delete(person);
    }

    public void addFriend(String personId, String friendId) {
        Person person = personRepo.findById(UUID.fromString(personId)).get();
        Person friend = personRepo.findById(UUID.fromString(friendId)).get();

        person.addFriend(friend);
        friend.addFriend(person);
        personRepo.save(person);
        personRepo.save(friend);
    }

    public void deleteFriend(String personId, String friendId) {
        Person person = personRepo.findById(UUID.fromString(personId)).get();
        Person friend = personRepo.findById(UUID.fromString(friendId)).get();

        person.removeFriend(friend);
        friend.removeFriend(person);
        personRepo.save(person);
        personRepo.save(friend);
    }

    public List<FriendDto> showFriends(String id) {
        Person person = personRepo.findById(UUID.fromString(id)).get();
        return person.getFriends().stream()
                .map(MappingPersonDTO::mapToFriendList)
                .collect(Collectors.toList());
    }

}
