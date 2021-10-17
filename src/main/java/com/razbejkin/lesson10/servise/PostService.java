package com.razbejkin.lesson10.servise;

import com.razbejkin.lesson10.dao.PersonDAO;
import com.razbejkin.lesson10.dao.PostDAO;
import com.razbejkin.lesson10.dto.PostDTO;
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

    private final PostDAO postDAO;
    private final PersonDAO personDAO;
    private final MappingPostDTO mappingPostDTO;
    private final PersonService personService;

    public List<PostDTO> findAllPosts(){
        return postDAO.findAll().stream()
                .map(mappingPostDTO::mapToPostDTO)
                .collect(Collectors.toList());
    }

    public void addNewPost(String id,String context){
        Person person = personDAO.findById(UUID.fromString(id)).get();
        person.addPost(context);
        personService.savePerson(person);
    }

    public void deletePost(String id){
        Posts post = postDAO.findById(UUID.fromString(id)).get();
        postDAO.delete(post);
    }

    public void updatePost(String id,String content){
        Posts post = postDAO.findById(UUID.fromString(id)).get();
        post.setContent(content);
        postDAO.save(post);
    }
}
