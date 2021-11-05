package com.razbejkin.lesson10.controller;

import com.razbejkin.lesson10.dto.PostDto;
import com.razbejkin.lesson10.servise.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson10/post")
@RequiredArgsConstructor
@Api(value = "Post Crud operation",description ="Post Crud operation" )
public class PostController {

    private final PostService postService;

    @ApiOperation(value = "find all post")
    @GetMapping
    public List<PostDto> findAllPost(){
        return postService.findAllPosts();
    }

    @ApiOperation(value = "create new post")
    @PostMapping("/{id}/new-post")
    public void createNewPost(@PathVariable("id") String id, @RequestBody String content){
        postService.addNewPost(id,content);
    }

    @ApiOperation(value = "delete post")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") String id){
        postService.deletePost(id);
    }

    @ApiOperation(value = "update post")
    @PutMapping("/{id}")
    public void updatePost(@PathVariable("id") String id, @RequestBody String content){
        postService.updatePost(id,content);
    }
}
