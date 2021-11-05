package com.razbejkin.lesson10.util;

import com.razbejkin.lesson10.dto.PostDto;
import com.razbejkin.lesson10.entity.Posts;

public class MappingPostDTO {

    public static PostDto mapToPostDTO(Posts posts){
        PostDto postDTO = new PostDto();
        postDTO.setId(posts.getId());
        postDTO.setContext(posts.getContent());
        return postDTO;
    }

}
