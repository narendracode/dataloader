package com.app.dbloader.exception;

public class InvalidStoreDataException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidStoreDataException(String errorMessage) {
        super(errorMessage);
    }
}
