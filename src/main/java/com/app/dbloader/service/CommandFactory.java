package com.app.dbloader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dbloader.Commands;

@Component
public class CommandFactory {

	@Autowired
	LoaderCommand loaderCommand;
	
	
	public Command getCommand(String command) {
		if(Commands.LOADER.toString().equals(command)) {
			return loaderCommand;
		}
		
		return null;
	}
}
