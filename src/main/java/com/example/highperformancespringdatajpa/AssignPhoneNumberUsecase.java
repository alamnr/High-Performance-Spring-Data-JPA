package com.example.highperformancespringdatajpa;

import org.apache.coyote.BadRequestException;
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

    @Transactional
    public void addAccount(Account account) throws BadRequestException{
        boolean exist = accountRepository.findById(account.getId()).isPresent();
        if(exist){
            throw new BadRequestException("Account  exist");
        }
        accountRepository.save(account);
    }
}
