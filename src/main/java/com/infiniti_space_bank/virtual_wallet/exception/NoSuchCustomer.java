package com.infiniti_space_bank.virtual_wallet.exception;

public class NoSuchCustomer extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchCustomer(int id) {
		super("no customer associated with id= " + id);
	}
}
