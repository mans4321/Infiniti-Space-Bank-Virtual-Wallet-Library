package com.infiniti_space_bank.virtual_wallet.spring.service;

import com.infiniti_space_bank.virtual_wallet.exception.NoSuchCustomer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;

public interface WalletService {

	public Customer getCustomer(int id) throws NoSuchCustomer;

	public void SaveOrUpdateCustomer(Customer customer);

	public void SaveOrUpdateAccount(Account account);

	public void SaveOrUpdateTransaction(Transaction transaction);
}
