package com.infiniti_space_bank.virtual_wallet.exception;

import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;

public class CustomerIsNotAccountOwenr extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerIsNotAccountOwenr(Account account) {
		super("Customer is not the owenr of this account" + "/n accountId = " + account.getAccountNumber());
	}
}
