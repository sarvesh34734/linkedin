package dev.svyas.connections_service.services.impl;

import dev.svyas.connections_service.entities.Person;
import dev.svyas.connections_service.repositories.PersonRepository;
import dev.svyas.connections_service.services.ConnectionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultConnectionsService implements ConnectionsService {

    private PersonRepository personRepository;

    public DefaultConnectionsService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getFirstDegreeConnections(Long userId) {
//        log.info("Getting first degree connections for user with id: {}",userId);
        return personRepository.getFirstDegreeConnections(userId);
    }

    @Override
    public Person createPerson(Person person) {
//        log.info("Creating a new person");
        return personRepository.save(person);
    }

    @Override
    public void addConnection(Long id1, Long id2) {
        Optional<Person> p1 = personRepository.findById(id1);
        Optional<Person> p2 = personRepository.findById(id2);
        if(p1.isEmpty() || p2.isEmpty()){
            throw new ResourceAccessException("Couldn't find both the users");
        }
        p1.get().getConnections().add(p2.get());
        p2.get().getConnections().add(p1.get());
        personRepository.save(p1.get());
        personRepository.save(p2.get());
    }
}
