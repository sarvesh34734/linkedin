package dev.svyas.linkedin.api_gateway.services;

public interface JwtService {

    String getUserIdFromJwt(String jwtToken);

}
