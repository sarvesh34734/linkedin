package dev.svyas.linkedin.user_service.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
