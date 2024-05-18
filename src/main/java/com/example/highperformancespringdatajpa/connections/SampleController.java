package com.example.highperformancespringdatajpa.connections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.highperformancespringdatajpa.dto.PersonDto;

import jakarta.validation.Valid;


@RestController

public class SampleController {
    private final SampleService sampleService;
    private final ExternalService externalService;
    public SampleController(SampleService sampleService, ExternalService externalService) {
        this.sampleService = sampleService;
        this.externalService = externalService;
    }

    @PostMapping("/person/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDto> crreateAccount(@Valid @RequestBody PersonDto personDto){
       
        return new ResponseEntity<>(sampleService.createPerson(personDto),HttpStatus.CREATED);
    }

    @GetMapping("/hello")

    String hello(){
        sampleService.hello();
        externalService.externalCall();
        return "Hello, World";
    }

    @GetMapping("/external")
    void external(){
        sampleService.withExternalServiceCall();
    }

    @GetMapping("/external-after")
    void externalAfter(){
        sampleService.withExternalServiceCallAfter();
    }

    @GetMapping("/nested")
    void nested(){
        sampleService.withNestedTransaction();
    }
}
