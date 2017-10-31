package ru.alexxsys.transmit_bots_message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.alexxsys.transmit_bots_message.configuration.ConfigTransferSystem;

@SpringBootApplication
public class TransmitBotsMessageApplication {

	public static void main(String[] args) {

		ApplicationContext context
				= SpringApplication.run(TransmitBotsMessageApplication.class, args);

		ConfigTransferSystem config = context.getBean(ConfigTransferSystem.class);
		config.setPathRemoteSystem("http://localhost:80/ChatBots");
		config.setEnableAutoSend(true);
		config.setLogin("online");
		config.setPassword("123");
	}
}
