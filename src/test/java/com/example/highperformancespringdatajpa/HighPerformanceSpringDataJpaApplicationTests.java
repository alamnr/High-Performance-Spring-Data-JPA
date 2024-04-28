package com.example.highperformancespringdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class HighPerformanceSpringDataJpaApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		// given
		int num1 = 20;
        int num2 = 30;

		// when 
		int result = underTest.add(num1, num2);

		// then
		int expected = 50; 
		assertThat(result).isEqualTo(expected);

	}

	class Calculator {
		public int add(int a, int b){
			return a+b;
		}
	}

}
