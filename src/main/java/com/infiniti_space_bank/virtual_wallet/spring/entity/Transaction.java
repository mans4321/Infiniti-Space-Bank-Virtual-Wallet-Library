package com.infiniti_space_bank.virtual_wallet.spring.entity;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.infiniti_space_bank.virtual_wallet.utility.TransactionType;

@Entity
@Table(name = "transaction_tab")
public class Transaction {

	// CREATE TABLE product (
	// `id` BINARY(16) NOT NULL primary key
	// ,`name` varchar(64)
	// ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	private UUID id;
	@Column(name = "transaction_type")
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	@Column(name = "amount")
	private double amount;
	@Column(name = "time_stamp")
	private Timestamp timestamp;
	@Column(name = "account_id")
	private int accountId;
	@Column(name = "remaining")
	private double remaining;

	public Transaction(int accountId, double amount, TransactionType type, double remaining) {
		this.type = type;
		this.amount = amount;
		this.remaining = remaining;
		this.accountId = accountId;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public Transaction() {
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", type=" + type + ", amount=" + amount + ", timestamp=" + timestamp
				+ ", accountId=" + accountId + ", remaining=" + remaining + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getRemaining() {
		return remaining;
	}

	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
