package com.example.highperformancespringdatajpa.domain;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase
@TestInstance(Lifecycle.PER_CLASS)
public class BankTransferRepositoryTest {

    @Autowired
    private BankTransferRepository bankTransferRepository;
    @Autowired
    private AccountRepository accountRepository;

    @BeforeAll
    void setUp(){
        Account sender = new Account("sender-id","iban1","John","Doe");
			sender.addPhoneNumber(new PhoneNumber("111",PhoneNumber.Type.HOME));
			sender = accountRepository.save(sender);
            var receiver = accountRepository.save(new Account("receiver-id", "iban2", "John", "Doe"));

            bankTransferRepository.save(new BankTransfer("bank-transfer-id", "reference2", sender, receiver, Amount.of(100, "CHF")));

            for (int i = 0; i < 50; i++) {
                Account account = accountRepository.save(new Account(
                        UUID.randomUUID().toString(), "iban2", "John", "Doe"));
                BankTransfer bankTransfer = new BankTransfer(UUID.randomUUID().toString(), "reference", sender,
                        account, Amount.of(100, "CHF"));
                bankTransferRepository.save(bankTransfer);
            }
    }

    @AfterAll
    void tearDown(){
        bankTransferRepository.deleteAll();
    }

    @Test
    void loadContext(){
        assertThat(accountRepository).isNotNull();
        assertThat(bankTransferRepository).isNotNull();
    }

    @Test
    void testFindByIdOrThrow() {

        // given 
        var transferId = "bank-transfer-id";
        // when 
        BankTransfer bankTransfer = bankTransferRepository.findByIdOrThrow(transferId);

        // then
        assertThat(transferId).isEqualTo(bankTransfer.getId());
    }

    @Test
    void testFindBySenderId() {
        // given
        var transferId ="fhghdghdfhlg";

        // when
        Throwable thrown = catchThrowable(()->{bankTransferRepository.findByIdOrThrow(transferId);});

        //  then 

        assertThat(thrown).hasMessage("No value present");

    }
}
