package dev.svyas.linkedin.user_service.services;

import dev.svyas.linkedin.user_service.entities.User;

public interface JwtService {

    String generateToken(User user);
}
