package dev.svyas.linkedin.post_service.services;

import dev.svyas.linkedin.post_service.dtos.CreatePostRequestDto;
import dev.svyas.linkedin.post_service.dtos.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(CreatePostRequestDto postRequestDto,Long userId);

    PostDto getPostById(Long id);

    List<PostDto> getAllPostsByUserId(Long userId);

}
