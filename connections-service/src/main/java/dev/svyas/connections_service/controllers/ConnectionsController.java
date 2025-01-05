package dev.svyas.connections_service.controllers;

import dev.svyas.connections_service.auth.UserContextHolder;
import dev.svyas.connections_service.entities.Person;
import dev.svyas.connections_service.services.ConnectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/core")
public class ConnectionsController {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionsController.class);

    private ConnectionsService connectionsService;

    public ConnectionsController(ConnectionsService connectionsService){
        this.connectionsService = connectionsService;
    }

    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(){
        LOG.info("user id in userContext - {}", UserContextHolder.getUserId());
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(UserContextHolder.getUserId()));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return ResponseEntity.ok(connectionsService.createPerson(person));
    }

    @PostMapping("/add-connection/{id1}/{id2}")
    public ResponseEntity<Void> addConnection(@PathVariable Long id1,@PathVariable Long id2){
        connectionsService.addConnection(id1,id2);
        return ResponseEntity.ok().build();
    }
}
