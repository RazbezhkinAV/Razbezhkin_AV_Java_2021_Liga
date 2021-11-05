package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.repo.PersonRepo;
import com.razbejkin.lesson10.repo.PostRepo;
import com.razbejkin.lesson10.dto.PostDto;
import com.razbejkin.lesson10.entity.Person;
import com.razbejkin.lesson10.entity.Posts;
import com.razbejkin.lesson10.util.MappingPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final PersonRepo personRepo;
    private final PersonService personService;

    public List<PostDto> findAllPosts(){
        return postRepo.findAll().stream()
                .map(MappingPostDTO::mapToPostDTO)
                .collect(Collectors.toList());
    }

    public void addNewPost(String id,String context){
        Person person = personRepo.findById(UUID.fromString(id)).get();
        person.addPost(context);
        personService.savePerson(person);
    }

    public void deletePost(String id){
        Posts post = postRepo.findById(UUID.fromString(id)).get();
        postRepo.delete(post);
    }

    public void updatePost(String id,String content){
        Posts post = postRepo.findById(UUID.fromString(id)).get();
        post.setContent(content);
        postRepo.save(post);
    }
}
