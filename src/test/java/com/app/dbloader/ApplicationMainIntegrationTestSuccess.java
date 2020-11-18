package com.app.dbloader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.dbloader.config.BatchTestConfig;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BatchTestConfig.class }, properties = { "data.file=sales-main.csv" }, args="LOADER")
public class ApplicationMainIntegrationTestSuccess  extends BaseDbloaderApplicationTests{
	@Test
	public void given_command_name_LOADER_when_resource_file_is_provided_then_it_should_load_the_data() throws Exception {
		assertThat(storeOrderRepository.count()).isEqualTo(9);
		clearData();
	}
}
