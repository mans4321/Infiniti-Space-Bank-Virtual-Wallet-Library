package com.infiniti_space_bank.virtual_wallet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.infiniti_space_bank.virtual_wallet.exception.CustomerIsNotAccountOwenr;
import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.exception.NoSuchCustomer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;
import com.infiniti_space_bank.virtual_wallet.spring.service.WalletService;
import com.infiniti_space_bank.virtual_wallet.utility.Wallet;

public class VirtualWalletImpl implements VirtualWallet {
	@Autowired
	private WalletService walletService;

	public Wallet createWallet(Customer customer) {
		Wallet wallet = new Wallet(customer, walletService);
		return wallet;
	}

	public double accountBalance(Wallet customerWallet) {
		return customerWallet.getBalance();
	}

	public double withdraw(Wallet customerWallet, double amount) throws InsufficientBalanceException {
		return customerWallet.withdraw(amount);
	}

	public double deposit(Wallet customerWallet, double amount) {
		return customerWallet.deposit(amount);
	}

	public List<Transaction> getTransaction(Wallet customerWallet, int N) {
		return customerWallet.getNTranscation(N);
	}

	public List<Account> getCustomerAccounts(Wallet customerWallet) {
		return customerWallet.getAccounts();
	}

	public void TrackAnotherAccount(Wallet customerWallet, Account account) throws CustomerIsNotAccountOwenr {
		customerWallet.setTrackedAccount(account);
	}

	public Customer getCustomer(int CustomerId) throws NoSuchCustomer {
		return walletService.getCustomer(CustomerId);
	}

}
