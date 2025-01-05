package dev.svyas.linkedin.api_gateway.filters;

import dev.svyas.linkedin.api_gateway.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService){
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("inside authentication filter");
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            if(null == authHeader || !authHeader.startsWith("Bearer")){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("AUTHORIZATION header not found");
                return exchange.getResponse().setComplete();
            }

            assert authHeader != null;
            try{
                String jwtToken = authHeader.split(" ")[1];
                String userId = jwtService.getUserIdFromJwt(jwtToken);

                // set user id header in requests
                exchange.mutate().request( r -> r.header("X-User-Id",userId)).build();
                return chain.filter(exchange);
            }
            catch(Exception ex){
                log.error(ex.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config{

    }

}
