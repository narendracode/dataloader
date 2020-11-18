package com.app.dbloader.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import com.app.dbloader.dto.StoreOrderCSV;

@EnableBatchProcessing
@Configuration
public class DBLoaderJobConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(DBLoaderJobConfig.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Value("${data.file:sales.csv}")
	public String fileToLoad;

	@Bean
	public ItemStreamReader<StoreOrderCSV> itemReader() {
		FlatFileItemReader<StoreOrderCSV> csvFileReader = new FlatFileItemReader<>();

		LOGGER.info("File to load : {}", fileToLoad);

		csvFileReader.setResource(new ClassPathResource(fileToLoad));
		csvFileReader.setLinesToSkip(1);

		LineMapper<StoreOrderCSV> storeOrderMapper = createStoreOrdertLineMapper();
		csvFileReader.setLineMapper(storeOrderMapper);

		return csvFileReader;
	}

	@Bean
	@JobScope
	public DBLoaderTasklet dBLoaderTasklet() {
		return new DBLoaderTasklet();
	}

	@Bean
	public Step csvFileToDatabaseStep() {
		DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setPropagationBehavior(Propagation.REQUIRED.value());
		attribute.setIsolationLevel(Isolation.DEFAULT.value());
		attribute.setTimeout(30);

		return stepBuilderFactory.get("csvFileToDatabaseStep").listener(new StepExecutionListenerSupport() {
			@Override
			public ExitStatus afterStep(StepExecution stepExecution) {
				return super.afterStep(stepExecution);
			}
		}).tasklet(dBLoaderTasklet()).transactionAttribute(attribute).build();
	}

	@Bean
	Job csvFileToDatabaseJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("csvFileToDatabaseJob").preventRestart().incrementer(new RunIdIncrementer())
				.listener(listener).flow(csvFileToDatabaseStep()).end().build();
	}

	private LineMapper<StoreOrderCSV> createStoreOrdertLineMapper() {
		DefaultLineMapper<StoreOrderCSV> storeOrderMapper = new DefaultLineMapper<>();

		LineTokenizer storeOrderTokenizer = createStoreOrderTokenizer();
		storeOrderMapper.setLineTokenizer(storeOrderTokenizer);

		FieldSetMapper<StoreOrderCSV> storeOrderInformationMapper = createStoreOrderInformationMapper();
		storeOrderMapper.setFieldSetMapper(storeOrderInformationMapper);

		return storeOrderMapper;
	}

	private LineTokenizer createStoreOrderTokenizer() {
		DelimitedLineTokenizer storeOrderTokenizer = new DelimitedLineTokenizer();
		storeOrderTokenizer.setDelimiter(",");
		storeOrderTokenizer.setNames(new String[] { "orderId", "orderDate", "shipDate", "shipMode", "customerId",
				"customerName", "productId", "category", "productName", "quantity", "discount", "profit" });

		storeOrderTokenizer.setIncludedFields(1, 2, 3, 4, 5, 6, 13, 15, 17, 18, 19, 20);

		return storeOrderTokenizer;
	}

	private FieldSetMapper<StoreOrderCSV> createStoreOrderInformationMapper() {
		BeanWrapperFieldSetMapper<StoreOrderCSV> storeOrderInformationMapper = new BeanWrapperFieldSetMapper<>();
		storeOrderInformationMapper.setTargetType(StoreOrderCSV.class);
		return storeOrderInformationMapper;
	}

}
