package com.example.highperformancespringdatajpa.domain;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankTransferRepository extends JpaRepository<BankTransfer, String> {

    default BankTransfer findByIdOrThrow(String id){
        return findById(id).orElseThrow();
    }

    //@Query("from BankTransfer b join fetch b.sender join fetch b.receiver where b.id =  :senderId")
    @EntityGraph(attributePaths={"sender","receiver"})
    List<BankTransfer> findBySenderId(String senderId);
    
}
