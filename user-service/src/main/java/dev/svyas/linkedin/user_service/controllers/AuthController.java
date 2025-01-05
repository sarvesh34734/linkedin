package dev.svyas.linkedin.user_service.controllers;

import dev.svyas.linkedin.user_service.dtos.SignInUserDto;
import dev.svyas.linkedin.user_service.dtos.SignUpRequestDto;
import dev.svyas.linkedin.user_service.dtos.UserDto;
import dev.svyas.linkedin.user_service.services.AuthService;
import dev.svyas.linkedin.user_service.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpRequestDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInUserDto signInUserDto, HttpServletResponse response){
        String token = authService.signIn(signInUserDto);
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
