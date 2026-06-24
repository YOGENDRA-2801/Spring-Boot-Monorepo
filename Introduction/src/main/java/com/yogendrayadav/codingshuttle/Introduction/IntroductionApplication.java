package com.yogendrayadav.codingshuttle.Introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntroductionApplication implements CommandLineRunner
{
	@Autowired
	private PaymentService paymentService1 ;
	@Autowired
	private PaymentService paymentService2 ;

	static void main(String[] args) {
		SpringApplication.run(IntroductionApplication.class, args);
//		System.out.println("Namaste World");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(paymentService1.hashCode());
		System.out.println(paymentService2.hashCode());

		paymentService1.pay();
		paymentService2.pay();
	}
}