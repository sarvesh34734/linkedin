package dev.svyas.linkedin.post_service.services.impl;

import dev.svyas.linkedin.post_service.dtos.CreatePostRequestDto;
import dev.svyas.linkedin.post_service.dtos.PostDto;
import dev.svyas.linkedin.post_service.entities.Post;
import dev.svyas.linkedin.post_service.exceptions.NotFoundException;
import dev.svyas.linkedin.post_service.repositories.PostRepository;
import dev.svyas.linkedin.post_service.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPostService implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(CreatePostRequestDto postRequestDto,Long userId) {

        Post newPost = modelMapper.map(postRequestDto,Post.class);
        newPost.setUserId(userId);
        Post createdPost = postRepository.save(newPost);

        return modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) throw new NotFoundException(String.format("post by id %s not found",id));
        return modelMapper.map(post.get(),PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        return postRepository.findAllByUserId(userId).stream().map(e -> modelMapper.map(e,PostDto.class)).toList();
    }
}
