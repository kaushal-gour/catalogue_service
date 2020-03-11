package com.sapient.catalouge.exception;

public class SellerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7059698699967100966L;
	
	public SellerNotFoundException(String message) {
		super(message);
	}

}
