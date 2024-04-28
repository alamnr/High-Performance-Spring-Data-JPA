package com.example.highperformancespringdatajpa.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BankTransferRepository extends JpaRepository<BankTransfer, String> {

    default BankTransfer findByIdOrThrow(String id){
        return findById(id).orElseThrow();
    }

    List<BankTransfer> findBySenderId(String senderId);
    
}
