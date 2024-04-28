package com.example.highperformancespringdatajpa;

import org.springframework.stereotype.Service;

import com.example.highperformancespringdatajpa.domain.Account;
import com.example.highperformancespringdatajpa.domain.AccountRepository;
import com.example.highperformancespringdatajpa.domain.PhoneNumber;

import jakarta.transaction.Transactional;

@Service
public class AssignPhoneNumberUsecase {
    private final AccountRepository accountRepository;

    public AssignPhoneNumberUsecase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void execute(String accountId, PhoneNumber phoneNumber){
        Account account = accountRepository.findByIdOrThrow(accountId);
        account.addPhoneNumber(phoneNumber);
        accountRepository.save(account);
    }
}
