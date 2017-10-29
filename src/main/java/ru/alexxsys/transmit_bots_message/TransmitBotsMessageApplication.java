package ru.alexxsys.transmit_bots_message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TransmitBotsMessageApplication {

	public static void main(String[] args) {

		ApplicationContext context
				= SpringApplication.run(TransmitBotsMessageApplication.class, args);

	}
}
