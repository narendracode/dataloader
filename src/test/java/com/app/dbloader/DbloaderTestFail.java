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
@SpringBootTest(classes = {BatchTestConfig.class}, properties = {"data.file=sales-invalid.csv"})
public class DbloaderTestFail  extends BaseDbloaderApplicationTests{
	@Test
	public void given_data_to_load_when_all_orderIds_are_not_unique__then_it_should_fail() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
	    assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("FAILED");
	    assertThat(storeOrderRepository.findAll().size()).isEqualTo(0);
	}
}