package com.infiniti_space_bank.virtual_wallet.utility;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.infiniti_space_bank.virtual_wallet.exception.CustomerIsNotAccountOwenr;
import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;
import com.infiniti_space_bank.virtual_wallet.spring.service.WalletService;

/**
 * The account will be accessed primarily using the wallet. All the relevant
 * operations will be exposed by the wallet only.
 * 
 * when the wallet initialize with customer info, the wallet will track the
 * first account was associated with a customer.
 * 
 * @author mans
 *
 */
public class Wallet {

	private WalletService walletService;
	private Customer customer;
	private Account trackedAccount;
	private Lock lock;

	/**
	 * Constructor of wallet.
	 * 
	 * @param customer
	 *            the customer to apply transaction on her/his account
	 */
	public Wallet(Customer customer, WalletService walletService) {
		this.customer = customer;
		trackedAccount = customer.getDefualtAccount();
		lock = new ReentrantLock();
		this.walletService = walletService;
	}

	public double getBalance() {
		lock.lock();
		double balance = trackedAccount.getBalance();
		lock.unlock();
		return balance;
	}

	public double withdraw(double amount) throws InsufficientBalanceException {
		lock.lock();
		double remaining;
		try {
			remaining = trackedAccount.withdraw(amount);
			System.out.println(walletService);
			walletService.SaveOrUpdateAccount(trackedAccount);

		} finally {
			lock.unlock();
			;
		}
		return remaining;
	}

	public double deposit(double amount) {
		lock.lock();
		double remaining = trackedAccount.deposit(amount);
		System.out.println(trackedAccount);
		walletService.SaveOrUpdateAccount(trackedAccount);
		lock.unlock();
		return remaining;

	}

	public List<Transaction> getNTranscation(int N) {
		return trackedAccount.getNTransaction(N);
	}

	public List<Account> getAccounts() {
		return customer.getAccounts();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getTrackedAccount() {
		return trackedAccount;
	}

	public void setTrackedAccount(Account trackedAccount) throws CustomerIsNotAccountOwenr {
		if (!customer.isOwener(trackedAccount)) {
			throw new CustomerIsNotAccountOwenr(trackedAccount);
		}
		this.trackedAccount = trackedAccount;
	}
}
