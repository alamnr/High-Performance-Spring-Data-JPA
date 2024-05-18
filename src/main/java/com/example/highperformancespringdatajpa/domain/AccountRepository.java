package com.example.highperformancespringdatajpa.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.highperformancespringdatajpa.record.NamesOnly;

public interface AccountRepository extends JpaRepository<Account, String> {

    default Account findByIdOrThrow(String id){
        return findById(id).orElseThrow();
    }

    @Query("from Account a left join fetch a.phoneNumbers ")
    //@EntityGraph(attributePaths="phoneNumbers")
    List<Account> findAllEager();
    

    // can use native query and for native query you should use interface instead of record 
    /*
     * interface NamesOnly {
     *   String getId();
     *   String getFirstName();
     *   String getLastName();
     * }
     * 
     */
    //@Query(value="select id , first_name, last_name from account where id = :id", nativeQuery= true)
    // can use cutom query HQL
    @Query("select new NamesOnly(a.id,a.firstName,a.lastName) from Account a where a.id = :id")
    NamesOnly findNamesOnlyById(String id);
    
    
}
