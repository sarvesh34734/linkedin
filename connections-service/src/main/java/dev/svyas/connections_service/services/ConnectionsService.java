package dev.svyas.connections_service.services;

import dev.svyas.connections_service.entities.Person;

import java.util.List;

public interface ConnectionsService {

    List<Person> getFirstDegreeConnections(Long userId);

    Person createPerson(Person person);

    void addConnection(Long id1,Long id2);
}
