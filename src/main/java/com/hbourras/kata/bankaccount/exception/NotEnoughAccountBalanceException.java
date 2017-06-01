package com.hbourras.kata.bankaccount.exception;

/**
 * Dedicated exception for operations which are not possible to be done
 * @author Hamid
 */
public class NotEnoughAccountBalanceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Balance is not enough to perform the operation";
	
	public NotEnoughAccountBalanceException() {
		super(DEFAULT_MSG);
	}
}
