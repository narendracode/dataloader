package com.app.dbloader;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.app.dbloader.config.CustomMySqlContainer;
import com.mysql.cj.jdbc.MysqlDataSource;


@Configuration
@Profile("test")
@Testcontainers
public class StandaloneDataConfig {
	
	@Container
    public static MySQLContainer<CustomMySqlContainer> mySQLContainer = CustomMySqlContainer.getInstance();
	
	static {
		if(!mySQLContainer.isRunning()) {
			mySQLContainer.start();
		}
	}
	
	
	  @DynamicPropertySource
	  static void mysqlProperties(DynamicPropertyRegistry registry) {
	    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
	    registry.add("spring.datasource.password", mySQLContainer::getPassword);
	    registry.add("spring.datasource.username", mySQLContainer::getUsername);
	  }
	
	
    @Bean
    public DataSource dataSource() {
    	MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(mySQLContainer.getJdbcUrl());
        dataSource.setUser(mySQLContainer.getUsername());
        dataSource.setPassword(mySQLContainer.getPassword());
        return dataSource;
    }
}
