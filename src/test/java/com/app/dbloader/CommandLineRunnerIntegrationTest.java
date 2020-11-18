package com.app.dbloader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.dbloader.config.BatchTestConfig;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BatchTestConfig.class }, properties = { "data.file=sales.csv" })
public class CommandLineRunnerIntegrationTest extends BaseDbloaderApplicationTests {
	@Autowired
	ApplicationContext ctx;



	@Test
	public void thatCommandLineRunnerDoesStuff() throws Exception {
		clearData();
		
		CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
		runner.run("LOADER");
		assertThat(storeOrderRepository.count()).isEqualTo(2);
		
		clearData();
	}
}