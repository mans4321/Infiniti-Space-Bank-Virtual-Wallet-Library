package com.infiniti_space_bank.virtual_wallet.spring.dao;

import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;

public interface WalletDao {

	public Customer getCustomer(int id);

	public void SaveOrUpdateCustomer(Customer customer);

	public void SaveOrUpdateAccount(Account account);

	public void SaveOrUpdateTransaction(Transaction transaction);
}
