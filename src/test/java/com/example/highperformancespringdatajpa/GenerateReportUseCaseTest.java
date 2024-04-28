package com.example.highperformancespringdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@UseCaseTest
public class GenerateReportUseCaseTest {
    
    @Autowired
    private GenerateReportUseCase useCase;

    @Test
    void executes(){
        useCase.execute("sender-id");
    }

}
