package dev.svyas.linkedin.post_service.services;

public interface PostLikeService {

    void likePost(Long postId, Long userId);

    void unlikePost(Long postId,Long userId);

}
