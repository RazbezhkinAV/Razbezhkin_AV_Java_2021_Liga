package com.razbejkin.lesson10.util;

import com.razbejkin.lesson10.dto.PostDTO;
import com.razbejkin.lesson10.entity.Posts;
import org.springframework.stereotype.Service;

@Service
public class MappingPostDTO {

    public PostDTO mapToPostDTO(Posts posts){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(posts.getId());
        postDTO.setContext(posts.getContent());
        return postDTO;
    }

}
