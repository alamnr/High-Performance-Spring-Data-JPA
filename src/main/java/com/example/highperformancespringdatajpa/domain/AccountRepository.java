package com.example.highperformancespringdatajpa.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    default Account findByIdOrThrow(String id){
        return findById(id).orElseThrow();
    }

    @Query("from Account a left join fetch a.phoneNumbers ")
    //@EntityGraph(attributePaths="phoneNumbers")
    List<Account> findAllEager();
    
}
