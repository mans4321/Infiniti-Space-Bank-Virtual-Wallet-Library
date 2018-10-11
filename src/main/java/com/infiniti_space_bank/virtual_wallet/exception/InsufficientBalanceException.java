package com.infiniti_space_bank.virtual_wallet.exception;

public class InsufficientBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(double available, double required) {
		super("Available $" + available + " but required $" + required);
	}
}
