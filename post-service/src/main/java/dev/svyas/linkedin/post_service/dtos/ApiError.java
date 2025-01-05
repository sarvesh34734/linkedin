package dev.svyas.linkedin.post_service.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiError {
    private String error;

    private String message;
}
