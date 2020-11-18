package com.app.dbloader.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

import com.app.dbloader.Commands;

@Service
public class LoaderCommand extends Command {
	protected static final Logger LOGGER = LoggerFactory.getLogger(LoaderCommand.class);

	public void execute() throws Exception {
		LOGGER.info("Starting job execution.");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("csvFileToDatabaseJob");

		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());
		builder.addLong("time", System.currentTimeMillis()).toJobParameters();

		JobExecution exec = jobLauncher.run(job, builder.toJobParameters());

		LOGGER.info("Job execution Complete with status : {}", exec.getStatus().getBatchStatus().name());
	}

	@Override
	public String name() {
		return Commands.LOADER.toString();
	}

}
