package com.infiniti_space_bank.virtual_wallet.spring.dao;

import org.springframework.stereotype.Repository;

import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;

@Repository("walletDao")
public class WalletDaoImp extends AbstractDao implements WalletDao {

	public Customer getCustomer(int id) {
		Customer customer = (Customer) getSession().get(Customer.class, id);
		return customer;
	}

	public void SaveOrUpdateCustomer(Customer customer) {
		saveOrUpdate(customer);
	}

	public void SaveOrUpdateAccount(Account account) {
		saveOrUpdate(account);
	}

	public void SaveOrUpdateTransaction(Transaction transaction) {
		saveOrUpdate(transaction);
	}

}
