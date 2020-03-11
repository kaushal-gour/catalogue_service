package com.sapient.catalouge.exception;

public class ProductNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 2068357817468444216L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}

