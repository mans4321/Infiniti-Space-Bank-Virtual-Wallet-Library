package com.infiniti_space_bank.virtual_wallet.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infiniti_space_bank.virtual_wallet.exception.NoSuchCustomer;
import com.infiniti_space_bank.virtual_wallet.spring.dao.WalletDao;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;

@Service("walletService")
@Transactional
public class WalletServiceImp implements WalletService {

	@Autowired
	private WalletDao walletDao;

	public Customer getCustomer(int id) throws NoSuchCustomer {
		Customer customer = walletDao.getCustomer(id);
		if (customer == null)
			throw new NoSuchCustomer(id);
		return customer;
	}

	public void SaveOrUpdateCustomer(Customer customer) {
		walletDao.SaveOrUpdateCustomer(customer);

	}

	public void SaveOrUpdateAccount(Account account) {
		walletDao.SaveOrUpdateAccount(account);
	}

	public void SaveOrUpdateTransaction(Transaction transaction) {
		walletDao.SaveOrUpdateTransaction(transaction);
	}

}
