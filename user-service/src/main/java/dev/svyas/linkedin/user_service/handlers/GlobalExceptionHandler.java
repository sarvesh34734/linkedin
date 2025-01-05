package dev.svyas.linkedin.user_service.handlers;

import dev.svyas.linkedin.user_service.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleAuthenticationException(AuthenticationException ex){

    }

}
