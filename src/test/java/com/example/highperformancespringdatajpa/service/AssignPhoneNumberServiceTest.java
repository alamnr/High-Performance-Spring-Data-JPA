package com.example.highperformancespringdatajpa.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.highperformancespringdatajpa.AssignPhoneNumberUsecase;
import com.example.highperformancespringdatajpa.domain.Account;
import com.example.highperformancespringdatajpa.domain.AccountRepository;
import com.example.highperformancespringdatajpa.domain.PhoneNumber;

@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
public class AssignPhoneNumberServiceTest {
    

    @Mock
    private AccountRepository accountRepository;
    @Mock
    Account account;
    private AutoCloseable autoCloseable;
    private AssignPhoneNumberUsecase assignPhoneNumberUsecase;

    @BeforeAll
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        assignPhoneNumberUsecase = new AssignPhoneNumberUsecase(accountRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }


    @Test
    void execute(){
        // given 
        String accountId = "any-id";
        
        PhoneNumber phoneNumber = new PhoneNumber("258", PhoneNumber.Type.HOME);
        // when
        when(accountRepository.findByIdOrThrow(accountId)).thenReturn(account);
        assignPhoneNumberUsecase.execute(accountId, phoneNumber);
        //then
        verify(accountRepository, atLeastOnce()).findByIdOrThrow(accountId);
        verify(account, atLeast(1)).addPhoneNumber(phoneNumber);
        verify(accountRepository, times(1)).save(account);
        //verify(accountRepository).deleteAll();
    }
}
