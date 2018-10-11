package com.infiniti_space_bank.virtual_wallet.spring.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.utility.TransactionType;

@Entity()
@Table(name = "account")
public class Account {


	@Id
	@Column(name = "id")
	private int accountId;
	@Column(name = "balance")
	private double balance;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private List<Transaction> transactionList;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "customer_id")
	private Customer owner;

	public Account() {
	};

	public Account(Customer owner) {
		this.owner = owner;
	}

	public double withdraw(double amount) throws InsufficientBalanceException {
		if ((balance - amount) < 0) {
			throw new InsufficientBalanceException(balance, amount);
		} else {
			balance -= amount;
			addTransaction(accountId, amount, TransactionType.WITHDRAWAL, balance);
			return balance;
		}

	}

	public double deposit(double amount) {
		balance += amount;
		addTransaction(accountId, amount, TransactionType.DEPOSIT, balance);
		return balance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", owner=[" + owner + "]]";
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public int getAccountNumber() {
		return accountId;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountId = accountNumber;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public List<Transaction> getNTransaction(int N) {
		if (transactionList != null) {
			if (transactionList.size() <= N) {
				return transactionList;
			} else {
				List<Transaction> NTransaction = new ArrayList<Transaction>();
				for (int i = transactionList.size() - 1; i >= 0; i--) {
					NTransaction.add(transactionList.get(i));
				}
				return NTransaction;
			}
		}
		return transactionList;
	}

	public void addTransaction(int accountNumber, double amount, TransactionType type, double remaining) {
		if (transactionList == null)
			transactionList = new ArrayList<Transaction>();
		Transaction transaction = new Transaction(accountNumber, amount, type, remaining);
		transactionList.add(transaction);
	}

}