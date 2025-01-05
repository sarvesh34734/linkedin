package dev.svyas.connections_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "dev.svyas.connections_service.repositories")
public class ConnectionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectionsServiceApplication.class, args);
	}

}
