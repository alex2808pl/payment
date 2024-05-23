package de.telran.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		//Payment system for the store
		SpringApplication.run(PaymentApplication.class, args);
	}
}
