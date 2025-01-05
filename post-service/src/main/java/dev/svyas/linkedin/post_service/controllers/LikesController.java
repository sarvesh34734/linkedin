package dev.svyas.linkedin.post_service.controllers;

import dev.svyas.linkedin.post_service.services.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
        postLikeService.likePost(postId,1L);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long postId){
        postLikeService.unlikePost(postId,1L);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
