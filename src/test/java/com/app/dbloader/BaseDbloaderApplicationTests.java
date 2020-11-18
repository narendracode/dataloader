package com.app.dbloader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import com.app.dbloader.config.CustomMySqlContainer;
import com.app.dbloader.repository.StoreOrderRepository;


public class BaseDbloaderApplicationTests  {
	@Container
	public static MySQLContainer<CustomMySqlContainer> mySQLContainer = CustomMySqlContainer.getInstance();
	
	@Autowired
	public JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	public StoreOrderRepository storeOrderRepository;
	
	@BeforeClass
	public static void setUp() {
		if(!mySQLContainer.isRunning()) {
			mySQLContainer.start();
		}
	}


	@AfterClass
	public static void tearDown() {
		mySQLContainer.stop();
	}
	
	public void clearData() {
		storeOrderRepository.deleteAll();
	}

}
