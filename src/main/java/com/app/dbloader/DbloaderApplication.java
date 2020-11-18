package com.app.dbloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.dbloader.service.Command;
import com.app.dbloader.service.CommandFactory;

@SpringBootApplication
public class DbloaderApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(DbloaderApplication.class);

	@Autowired
	CommandFactory commandFactory;

	public static void main(String[] args) {
		SpringApplication.run(DbloaderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Run command found {}", (args.length > 0));

		String command = null;
		if (args.length > 0 && !args[0].isEmpty()) {
			command = args[0];
		}

		if (null == command) {
			return;
		}

		LOGGER.info("Run command {}", command);
		Command cmd = commandFactory.getCommand(args[0]);
		if (cmd != null) {
			cmd.execute();
		}

		LOGGER.info("Command executed {}", cmd.name());
	}

}
