package com.example.highperformancespringdatajpa;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.highperformancespringdatajpa.domain.PhoneNumber;

@UseCaseTest
public class AssignPhoneNumberUseCaseTest {

    @Autowired
    AssignPhoneNumberUsecase useCase;

    @Test
    void execute(){
        useCase.execute("sender-id", new PhoneNumber("999", PhoneNumber.Type.HOME));
    }

    
}
