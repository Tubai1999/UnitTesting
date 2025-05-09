package com.Testing.MockitoTesting;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MockitoTestingApplicationTests {

	@BeforeEach
	void method1(){
		System.out.println("execute before every test method");
	}

	@AfterEach
	void method2(){
		System.out.println("execute after every test method");
	}

	@Test
	void testForExceptionHandling() {
		int a = 5;
		int b = 0;
		Assertions.assertThatThrownBy(()->divideTwoNumber(a,b))
				.isInstanceOf(Exception.class);
	}

	@Test
//	@DisplayName("twoSum")
	void testMethodForSumOfTwoNumber(){
		int a = 1;
		int b = 9;
		int result = twoSum(a,b);
		Assertions.assertThat(result).isEqualTo(10);
	}




	int twoSum(int a, int b){
		return a+b;
	}

	double divideTwoNumber(int a, int b) throws Exception {
		try{
			return a/b;

		}catch (Exception e){
			System.out.println(e);
			throw new Exception();
		}
//        return 0;
    }

}
