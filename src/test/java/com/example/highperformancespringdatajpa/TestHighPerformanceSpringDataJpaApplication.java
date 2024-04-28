package com.example.highperformancespringdatajpa;

import java.util.UUID;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.highperformancespringdatajpa.domain.Account;
import com.example.highperformancespringdatajpa.domain.AccountRepository;
import com.example.highperformancespringdatajpa.domain.Amount;
import com.example.highperformancespringdatajpa.domain.BankTransfer;
import com.example.highperformancespringdatajpa.domain.BankTransferRepository;
import com.example.highperformancespringdatajpa.domain.PhoneNumber;

@TestConfiguration(proxyBeanMethods = false)
public class TestHighPerformanceSpringDataJpaApplication {

	// @Bean
	// @ServiceConnection
	// PostgreSQLContainer<?> postgresContainer() {
	// 	return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest")).withReuse(true);
	// }

	@Bean
	ApplicationRunner applicationRunner(AccountRepository accountRepository, BankTransferRepository bankTransferRepository) {
		return args -> {
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
        };

	}

	public static void main(String[] args) {
		SpringApplication.from(HighPerformanceSpringDataJpaApplication::main).with(TestHighPerformanceSpringDataJpaApplication.class).run(args);
	}

}
