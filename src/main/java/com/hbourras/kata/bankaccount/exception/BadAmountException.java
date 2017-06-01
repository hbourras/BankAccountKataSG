package com.hbourras.kata.bankaccount.exception;

/**
 * Dedicated exception for bad amount handled into operation
 * @author Hamid
 */
public class BadAmountException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Amount should be positive and non-null";
	
	public BadAmountException() {
		super(DEFAULT_MSG);
	}
}
