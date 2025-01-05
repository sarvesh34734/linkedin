package dev.svyas.linkedin.post_service.services.impl;

import dev.svyas.linkedin.post_service.entities.PostLike;
import dev.svyas.linkedin.post_service.exceptions.NotFoundException;
import dev.svyas.linkedin.post_service.exceptions.PostLikeException;
import dev.svyas.linkedin.post_service.repositories.PostLikeRepository;
import dev.svyas.linkedin.post_service.repositories.PostRepository;
import dev.svyas.linkedin.post_service.services.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultPostLikeService implements PostLikeService {

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    @Override
    public void likePost(Long postId, Long userId) {
        boolean doesPostExist = postRepository.existsById(postId);
        if(!doesPostExist) throw new NotFoundException(String.format("No post with id %s exists",postId));

        boolean doesLikeExist = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(doesLikeExist) throw new PostLikeException("You have already liked this post");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
    }

    @Override
    public void unlikePost(Long postId, Long userId) {
        Optional<PostLike> postLike = postLikeRepository.findByUserIdAndPostId(userId,postId);
        if(postLike.isEmpty()) throw new PostLikeException("You haven't liked the post, so you can't unlike");
        postLikeRepository.delete(postLike.get());
    }
}
