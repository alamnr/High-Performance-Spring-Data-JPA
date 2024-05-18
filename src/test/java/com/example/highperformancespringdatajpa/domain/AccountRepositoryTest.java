package com.example.highperformancespringdatajpa.domain;

import java.util.List;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.highperformancespringdatajpa.domain.PhoneNumber.Type;
import com.example.highperformancespringdatajpa.record.NamesOnly;

import jakarta.persistence.EntityManager;

@DataJpaTest
//@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
public class AccountRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private DataSource ds;
    @Autowired
    private AccountRepository accountRepository;

    @BeforeAll
    void setUp(){
       	Address address = new Address("Mirpur", "Dhaka", "1216");
		PhoneNumber ph1 = new PhoneNumber("9870453",Type.HOME);
		PhoneNumber ph2 = new PhoneNumber("9870443",Type.WORK);
		Account act =  new Account("sender-id1", "iban", "Karim", "Ullah");
		act.addPhoneNumber(ph1);
		act.addPhoneNumber(ph2);
		act.addAddress(address);
		accountRepository.save(act);
		Account act2 =  new Account("sender-id2", "iban2", "Rahim", "Mia");
		act2.addPhoneNumber(ph2);
		act2.addAddress(address);
		accountRepository.save(act2);
   
    }

    @AfterAll
    void tearDown(){
        accountRepository.deleteAll();
    }
    @Test
    void itShouldTestAnAcoountWithGivenIdExist(){
        // given 
        Account acct = new Account("acct-1","iban-1","acct1-firstName","acct1-lastName");
        accountRepository.save(acct);
        // when
        boolean  expected  = accountRepository.findById("acct-1").isPresent();


        //then

        assertThat(expected).isEqualTo(true);

    }

    @Test
    void contextLoad(){
        assertThat(em).isNotNull();
        assertThat(ds).isNotNull();
        assertThat(accountRepository).isNotNull();

    }

    @Test
    void itShouldTestChildEntityLoadingIsLazyButFetchedEgerly_FindAllEager() {
        // given 
        List<Account> acctList = accountRepository.findAllEager();

        // when 

        // then
        assertThat(acctList.size()).isGreaterThan(0);
        assertThat(acctList.get(0).getPhoneNumbers()).hasSize(2);
        assertThat(acctList.get(1).getPhoneNumbers()).hasSize(1);

    }

    @Test
    void itShouldTestProjectionOfAccountByNamesOnly(){
        // given 
        NamesOnly namesOnly = accountRepository.findNamesOnlyById("sender-id2");
        // when

        // then
        assertThat(namesOnly).isNotNull();
        System.out.println(namesOnly); 
      }
    @Test
    void itShouldTestFindByIdOrThrow() {

        // given 
        String accountId =  "sender-id23";

        // when
        Throwable thrown  = catchThrowable(()->{accountRepository.findByIdOrThrow(accountId);});
        // then 
        
        assertThat(thrown).hasMessage("No value present");
        
    }
}
