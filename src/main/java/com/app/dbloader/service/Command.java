package com.app.dbloader.service;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class Command {
	@Autowired
	ApplicationContext context;
	
	@Autowired
	Job csvFileToDatabaseJob;
	
	public abstract void execute()  throws Exception;
	public abstract String name();

}
