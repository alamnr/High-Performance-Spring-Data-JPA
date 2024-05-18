package com.example.highperformancespringdatajpa.connections;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.highperformancespringdatajpa.dto.PersonDto;

@Service
public class SampleService {
    private final AnotherService anotherService;
    private final ExternalService externalService;
    private final PersonRepository personRepository;
    private final TransactionTemplate transactionTemplate;
    public SampleService(AnotherService anotherService, ExternalService externalService, PersonRepository personRepository, org.springframework.transaction.support.TransactionTemplate transactionTemplate) {
        this.anotherService = anotherService;
        this.externalService = externalService;
        this.personRepository = personRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Transactional
    public PersonDto createPerson(PersonDto personDto){
        Person entity =  Person.builder().id(personDto.id()).name(personDto.name()).build();
        Person newEntity = personRepository.save(entity);

        PersonDto personResponse = new PersonDto(newEntity.getId(),newEntity.getName());

        return personResponse;
    }

    @Transactional
    public void hello(){
        System.out.println(personRepository.findAll());
    }

    public String hi(){
        return "Hi";
    }

    @Transactional
    public void withExternalServiceCall(){
        externalService.externalCall();
        System.out.println(personRepository.findAll());
    }

    //@Transactional
    public void withExternalServiceCallAfter(){
        transactionTemplate.executeWithoutResult(transactionStatus -> System.out.println(personRepository.findAll()));
        //System.out.println(personRepository.findAll());
        externalService.externalCall();
    }

    @Transactional
    public void withNestedTransaction(){
        System.out.println(personRepository.findAll());
        anotherService.runInNewTransaction();
    }



}
