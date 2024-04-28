package com.example.highperformancespringdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@UseCaseTest
public class SettleBankTransferUsecaseTest {
    
    @Autowired
    private SettleBankTransferUseCase useCase;

    @Test
    void executes() {
        useCase.execute("bank-transfer-id");
    }
}
