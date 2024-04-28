package com.example.highperformancespringdatajpa;

import org.springframework.stereotype.Service;

import com.example.highperformancespringdatajpa.domain.Account;
import com.example.highperformancespringdatajpa.domain.AccountRepository;
import com.example.highperformancespringdatajpa.domain.Amount;
import com.example.highperformancespringdatajpa.domain.BankTransfer;
import com.example.highperformancespringdatajpa.domain.BankTransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterBankTransferUseCase {

    private final AccountRepository accountRepository;
    private final BankTransferRepository bankTransferRepository;
    

    public void execute(String bankTransferId,String reference, String senderId, String receiverId, Amount amount){
        Account sender = accountRepository.findByIdOrThrow(senderId);
        Account receiver = accountRepository.findByIdOrThrow(receiverId) ;

        BankTransfer bankTransfer = new BankTransfer(bankTransferId,reference, sender, receiver, amount);
        bankTransferRepository.save(bankTransfer);
    }
    
}
