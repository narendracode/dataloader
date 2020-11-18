package com.app.dbloader.config;

import org.testcontainers.containers.MySQLContainer;

public class CustomMySqlContainer extends MySQLContainer<CustomMySqlContainer>{
	private static CustomMySqlContainer customMySqlContainer; 
	
	private static final String IMAGE_VERSION = "mysql:latest";
	
	private CustomMySqlContainer() {
        super(IMAGE_VERSION);
    }

    public static CustomMySqlContainer getInstance() {
        if (customMySqlContainer == null) {
        	customMySqlContainer = new CustomMySqlContainer();
        	customMySqlContainer.runInitScriptIfRequired();
        	customMySqlContainer.withInitScript("schema.sql");
        }
        return customMySqlContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", customMySqlContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", customMySqlContainer.getUsername());
        System.setProperty("DB_PASSWORD", customMySqlContainer.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
	
}
