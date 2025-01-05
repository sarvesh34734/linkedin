package dev.svyas.linkedin.user_service.services.impl;

import dev.svyas.linkedin.user_service.PasswordUtils;
import dev.svyas.linkedin.user_service.dtos.SignInUserDto;
import dev.svyas.linkedin.user_service.dtos.SignUpRequestDto;
import dev.svyas.linkedin.user_service.dtos.UserDto;
import dev.svyas.linkedin.user_service.entities.User;
import dev.svyas.linkedin.user_service.exceptions.AuthenticationException;
import dev.svyas.linkedin.user_service.repositories.UserRepository;
import dev.svyas.linkedin.user_service.services.AuthService;
import dev.svyas.linkedin.user_service.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthService implements AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    @Override
    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = modelMapper.map(signUpRequestDto,User.class);
        user.setPassword(PasswordUtils.encryptPass(signUpRequestDto.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public String signIn(SignInUserDto signInUserDto) {
        Optional<User> optUser = userRepository.findByEmail(signInUserDto.getEmail());
        if(optUser.isEmpty()) throw new ResourceAccessException("no such user exists");
        if(!PasswordUtils.verifyPw(signInUserDto.getPassword(),optUser.get().getPassword())){
            throw new AuthenticationException("couldn't authenticate the user");
        }
        return jwtService.generateToken(optUser.get());
    }
}
