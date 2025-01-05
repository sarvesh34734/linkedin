package dev.svyas.linkedin.post_service.exceptions;

import dev.svyas.linkedin.post_service.repositories.PostLikeRepository;

public class PostLikeException extends RuntimeException{
    public PostLikeException(String message){
        super(message);
    }
}
