package com.app.dbloader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.dbloader.Commands;
import com.app.dbloader.config.BatchTestConfig;
import com.app.dbloader.service.CommandFactory;
import com.app.dbloader.service.LoaderCommand;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BatchTestConfig.class }, properties = { "data.file=sales.csv" })
public class CommandFactoryTest extends BaseDbloaderApplicationTests {
	
	@Autowired
	CommandFactory factory;
	
	@Test
	public void given_when_command_is_valid_then_return_command_object() {
		assertThat(factory.getCommand(Commands.LOADER.toString()).getClass()).isEqualTo(LoaderCommand.class);
		clearData();
	}
	
	@Test
	public void given_when_command_is_invalid_then_return_null() {
		assertThat(factory.getCommand("invalid")).isEqualTo(null);
		clearData();
	}

}
