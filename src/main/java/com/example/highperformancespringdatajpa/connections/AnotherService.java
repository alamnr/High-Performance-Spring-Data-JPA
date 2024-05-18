package com.example.highperformancespringdatajpa.connections;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class AnotherService {
    
    private final PersonRepository personRepository;
    private final TransactionTemplate transactionTemplate;
    public AnotherService(PersonRepository personRepository, TransactionTemplate transactionTemplate){
        this.personRepository = personRepository;
        this.transactionTemplate = transactionTemplate;
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runInNewTransaction(){
        transactionTemplate.executeWithoutResult(transactionResult -> System.out.println(personRepository.findAll()));        
        //System.out.println(personRepository.findAll());
        Sleep.sleep(400);
    }
    
}
