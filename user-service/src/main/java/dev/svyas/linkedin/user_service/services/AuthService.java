package dev.svyas.linkedin.user_service.services;

import dev.svyas.linkedin.user_service.dtos.SignInUserDto;
import dev.svyas.linkedin.user_service.dtos.SignUpRequestDto;
import dev.svyas.linkedin.user_service.dtos.UserDto;

public interface AuthService {

    UserDto signUp(SignUpRequestDto signUpRequestDto);

    String signIn(SignInUserDto signInUserDto);

}
