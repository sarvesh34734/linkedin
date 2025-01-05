package dev.svyas.linkedin.post_service.handlers;

import dev.svyas.linkedin.post_service.dtos.ApiError;
import dev.svyas.linkedin.post_service.exceptions.NotFoundException;
import dev.svyas.linkedin.post_service.exceptions.PostLikeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiError> handleNotFoundException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError
                .builder()
                .error(ex.getClass().getSimpleName().replace("Exception","Error"))
                .message(ex.getMessage()).build());
    }

    @ExceptionHandler({PostLikeException.class})
    public ResponseEntity<ApiError> handlePostLikeException(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError
                .builder()
                .error(ex.getClass().getSimpleName().replace("Exception","Error"))
                .message(ex.getMessage()).build());
    }

}
