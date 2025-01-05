package dev.svyas.linkedin.post_service.clients;

import dev.svyas.linkedin.post_service.dtos.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="connections-service",path="/connections")
public interface ConnectionServiceFeignClient {

    @GetMapping("/core/first-degree")
    ResponseEntity<List<PersonDto>> getFirstDegreeConnections();
}
