package com.example.highperformancespringdatajpa;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.highperformancespringdatajpa.domain.Amount;

@UseCaseTest
public class RegisterBankTransferUseCaseTest {
    
    @Autowired
    private RegisterBankTransferUseCase useCase;

    @Test
    void executes() {
        useCase.execute(UUID.randomUUID().toString(), "my reference", "sender-id", "receiver-id", Amount.of(10, "EUR"));
    }
}
