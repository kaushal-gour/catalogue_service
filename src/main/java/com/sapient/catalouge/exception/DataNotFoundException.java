package com.sapient.catalouge.exception;

public class DataNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 795262659864477398L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
