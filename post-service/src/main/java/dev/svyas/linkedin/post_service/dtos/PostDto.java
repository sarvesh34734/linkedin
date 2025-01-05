package dev.svyas.linkedin.post_service.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {

    private Long id;

    private String content;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
