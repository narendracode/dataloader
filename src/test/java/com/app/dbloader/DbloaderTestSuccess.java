package com.app.dbloader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.dbloader.config.BatchTestConfig;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchTestConfig.class}, properties = {"data.file=sales.csv"})
class DbloaderTestSuccess  extends BaseDbloaderApplicationTests{
	@Test
	public void given_data_to_load_when_all_rows_are_unique_then_it_should_load_data_successfully() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
	    assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
	    assertThat(storeOrderRepository.findAll().size()).isEqualTo(2);
	    clearData();
	}
}