package dev.svyas.linkedin.user_service.dtos;

import lombok.Data;

@Data
public class SignInUserDto {
    private String email;
    private String password;
}
