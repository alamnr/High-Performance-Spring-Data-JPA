package com.example.highperformancespringdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.highperformancespringdatajpa.domain.Account;
import com.example.highperformancespringdatajpa.domain.AccountRepository;
import com.example.highperformancespringdatajpa.domain.Address;
import com.example.highperformancespringdatajpa.domain.PhoneNumber;
import com.example.highperformancespringdatajpa.domain.PhoneNumber.Type;

/**
 *
 * @author Alam
 */
@SpringBootApplication
//public class HighPerformanceSpringDataJpaApplication implements CommandLineRunner {

	public class HighPerformanceSpringDataJpaApplication {

	// @Autowired
	// AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(HighPerformanceSpringDataJpaApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {

	// 	Address address = new Address("Mirpur", "Dhaka", "1216");
	// 	PhoneNumber ph1 = new PhoneNumber("9870453",Type.HOME);
	// 	PhoneNumber ph2 = new PhoneNumber("9870443",Type.WORK);
	// 	Account act =  new Account("sender-id1", "iban", "Karim", "Ullah");
	// 	act.addPhoneNumber(ph1);
	// 	act.addPhoneNumber(ph2);
	// 	act.addAddress(address);
	// 	accountRepository.save(act);
	// 	Account act2 =  new Account("sender-id2", "iban2", "Rahim", "Mia");
	// 	act2.addPhoneNumber(ph2);
	// 	act2.addAddress(address);
	// 	accountRepository.save(act2);

	// 	accountRepository.findAllEager().forEach(System.out::println);
	// }

}
