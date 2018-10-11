package com.infiniti_space_bank.virtual_wallet.api;

/*
 * dataBase almost done 
 * maven  
 * test
 * doc
 */
import java.util.List;

import com.infiniti_space_bank.virtual_wallet.exception.CustomerIsNotAccountOwenr;
import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;
import com.infiniti_space_bank.virtual_wallet.utility.Wallet;

/**
 * 
 * The library provide clear public endpoints to ● Create a new wallet for a
 * customer ● Return current account balance ● Perform a withdrawal transaction
 * on the account ● Perform a deposit transaction on the account ● Return last N
 * transactions
 *
 * The account will be accessed primarily using the wallet. This means before
 * applying any operation on an account a wallet has to be created then the
 * wallet will be used to apply operation on the account
 * 
 * @author mans
 *
 */
public interface VirtualWallet {
	// TODO getCustomer

	/**
	 * Create a new wallet for a customer
	 * 
	 * @param customer
	 *            the customer to apply transaction on her/his account
	 * @return Wallet the account will be accessed primarily using the wallet.
	 *         All the relevant operations will be exposed by the wallet only.
	 */
	public Wallet createWallet(Customer customer); // what should return

	/**
	 * get the account balance
	 * 
	 * @param customerWallet
	 * @return account balance
	 */
	public double accountBalance(Wallet customerWallet);

	/**
	 * withdraw
	 * 
	 * @param customerWallet
	 * @param amount
	 *            the amount to withdraw
	 * @return the balance after withdraw
	 * @throws InsufficientBalanceException
	 *             if the account does not have sufficient balance
	 */
	public double withdraw(Wallet customerWallet, double amount) throws InsufficientBalanceException;

	/**
	 * deposit
	 * 
	 * @param customerWallet
	 *            the account will be accessed primarily using the wallet
	 * @param amount
	 *            the amount to deposit
	 * @return the balance after withdraw
	 */
	public double deposit(Wallet customerWallet, double amount);

	/**
	 * get the last N transactions
	 * 
	 * @param customerWallet
	 *            the account will be accessed primarily using the walle
	 * @param N
	 *            the number of transactions will be retrieved
	 * @return list of N transactions or Null if no Transactions have occurred
	 */
	public List<Transaction> getTransaction(Wallet customerWallet, int N);

	/**
	 * get list of accounts owned by the customer
	 * 
	 * @param customerWallet
	 *            the account will be accessed primarily using the wallet
	 * @return list of accounts owned by the customer
	 */
	public List<Account> getCustomerAccounts(Wallet customerWallet);

	/**
	 * 
	 * apply transactions on a different account
	 * 
	 * @param customerWallet
	 *            the account will be accessed primarily using the wallet
	 * @param account
	 *            the new account to track
	 * @throws CustomerIsNotAccountOwenr
	 *             if the account is not owned by the customer associated with
	 *             the wallet
	 */
	public void TrackAnotherAccount(Wallet customerWallet, Account account) throws CustomerIsNotAccountOwenr;

}
