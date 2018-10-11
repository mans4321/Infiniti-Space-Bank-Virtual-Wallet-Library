package com.infiniti_space_bank.virtual_wallet.utility;

public enum TransactionType {

	WITHDRAWAL("Withdraw"), DEPOSIT("Deposit"), REVERSAL("reversal");

	private String transType;

	private TransactionType(String transType) {
		this.transType = transType;
	}

	@Override
	public String toString() {
		return transType;
	}
}
