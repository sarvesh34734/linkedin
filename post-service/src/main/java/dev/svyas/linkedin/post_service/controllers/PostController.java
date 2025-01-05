package dev.svyas.linkedin.post_service.controllers;

import dev.svyas.linkedin.post_service.auth.UserContextHolder;
import dev.svyas.linkedin.post_service.clients.ConnectionServiceFeignClient;
import dev.svyas.linkedin.post_service.dtos.CreatePostRequestDto;
import dev.svyas.linkedin.post_service.dtos.PersonDto;
import dev.svyas.linkedin.post_service.dtos.PostDto;
import dev.svyas.linkedin.post_service.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/core")
@Slf4j
public class PostController {

    private final PostService postService;

    private final ConnectionServiceFeignClient connectionServiceFeignClient;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequestDto postRequestDto){
        PostDto createdPost = postService.createPost(postRequestDto,1L);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        log.info("user id header - {}", UserContextHolder.getUserId());
        List<PersonDto> connections = connectionServiceFeignClient.getFirstDegreeConnections().getBody();
        log.info("Connections from feign client - {}",connections);
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getAllPostsByUserId(userId));
    }
}
