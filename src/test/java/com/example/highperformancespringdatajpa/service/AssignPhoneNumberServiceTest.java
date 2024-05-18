package com.example.highperformancespringdatajpa.service;


import java.util.Optional;

import org.apache.coyote.BadRequestException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.given;
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


    @Disabled
    @Test
    void addAccount() throws BadRequestException{
        // given 
        Account acct = new Account("id-1","iban","afdaf","fsaffdaf");
        // when
        assignPhoneNumberUsecase.addAccount(acct);

        // then
        ArgumentCaptor<Account> acctArgumentCaptor = ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).save(acctArgumentCaptor.capture());

        Account capturedAccount = acctArgumentCaptor.getValue();
        assertThat(capturedAccount).isEqualTo(acct);

    }

    @Disabled
    @Test
    void willThrowWhenAccountIsTaken(){
        // given 
        Account acct = new Account("id-1","iban","afdaf","fsaffdaf");
        given(accountRepository.findById(acct.getId())).willReturn(Optional.of(acct));
        // when
        // then 
        assertThatThrownBy(() -> assignPhoneNumberUsecase.addAccount(account))
            .isInstanceOf(BadRequestException.class)
            .hasMessageContaining("Account  exist");
        
        
    }
}
