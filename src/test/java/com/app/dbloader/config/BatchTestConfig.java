package com.app.dbloader.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.app.dbloader.config.DBLoaderJobConfig;

@TestConfiguration
@Import({DBLoaderJobConfig.class})
public class BatchTestConfig {

	@Autowired
    private Job csvFileToDatabaseJob;

	
	@Bean
    JobLauncherTestUtils jobLauncherTestUtils() throws NoSuchJobException {
      JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
      jobLauncherTestUtils.setJob(csvFileToDatabaseJob);

      return jobLauncherTestUtils;
    }
}
