package ua.plaranjinha.tqs_hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TqsHwApplication {

	public static void main(String[] args) {
		SpringApplication.run(TqsHwApplication.class, args);
	}

	//TODO:
	// Input validation / Exception handling (REST country code length (the nulls may be enough))
	// Decent looking and working frontend (with data formatting)
	// Logging (which doesnt mess with testing)
	// Selenium tests
	// 14/05 - Deliver report of the WHOLE app
}
