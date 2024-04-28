package com.example.highperformancespringdatajpa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.highperformancespringdatajpa.domain.BankTransfer;
import com.example.highperformancespringdatajpa.domain.BankTransferRepository;

import jakarta.transaction.Transactional;

@Service
public class GenerateReportUseCase {

    private final BankTransferRepository bankTransferRepository;

    public GenerateReportUseCase(BankTransferRepository bankTransferRepository) {
        this.bankTransferRepository = bankTransferRepository;
    }


    @Transactional
    public void execute(String senderId){
        List<BankTransfer> entries = bankTransferRepository.findBySenderId(senderId);
        for (BankTransfer element : entries) {
            System.out.println(element.getReceiver().getIban());
        }
    }
    
}
