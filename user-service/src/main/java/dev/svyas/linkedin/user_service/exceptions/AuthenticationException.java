package dev.svyas.linkedin.user_service.exceptions;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message){
        super(message);
    }

}
