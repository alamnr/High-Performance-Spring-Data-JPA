package com.example.highperformancespringdatajpa;


import org.junit.jupiter.api.Test;
import org.quickperf.sql.annotation.ExpectDelete;
import org.quickperf.sql.annotation.ExpectSelect;
import org.quickperf.sql.annotation.ExpectUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.highperformancespringdatajpa.domain.PhoneNumber;

@UseCaseTest
public class AssignPhoneNumberUseCaseTest {

    @Autowired
    AssignPhoneNumberUsecase useCase;

    @Test
    @ExpectSelect(1)
    @ExpectUpdate(1)
    @ExpectDelete(0)
    void execute(){
        useCase.execute("sender-id", new PhoneNumber("999", PhoneNumber.Type.HOME));
    }

    
}
