package com.razbejkin.lesson10.controller;

import com.razbejkin.lesson10.dto.PostDTO;
import com.razbejkin.lesson10.servise.PersonService;
import com.razbejkin.lesson10.servise.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> findAllPost(){
        return postService.findAllPosts();
    }

    @PostMapping("/{id}/newPost")
    public String createNewPost(@PathVariable("id") String id, @RequestBody String content){
        postService.addNewPost(id,content);
        return "Create new Post";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") String id){
        postService.deletePost(id);
        return "Post delete";
    }

    @PutMapping("/{id}")
    public String updatePost(@PathVariable("id") String id, @RequestBody String content){
        postService.updatePost(id,content);
        return "Post update";
    }
}
