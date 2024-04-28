package com.example.highperformancespringdatajpa;

import org.springframework.stereotype.Service;

import com.example.highperformancespringdatajpa.domain.BankTransfer;
import com.example.highperformancespringdatajpa.domain.BankTransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettleBankTransferUseCase {
    
    private final BankTransferRepository bankTransferRepository;

    public void execute(String bankTransferId){
        BankTransfer bankTransfer = bankTransferRepository.findByIdOrThrow(bankTransferId);
        bankTransfer.settle();
        bankTransferRepository.save(bankTransfer);
    }


}
