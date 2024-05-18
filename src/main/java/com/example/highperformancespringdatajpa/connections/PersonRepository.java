package com.example.highperformancespringdatajpa.connections;

import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends  ListCrudRepository<Person, String> {
    
}
