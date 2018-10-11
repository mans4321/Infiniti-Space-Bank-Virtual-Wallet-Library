package com.infiniti_space_bank.virtual_wallet.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//User can have multiple transaction accounts

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "country")
	private String country;
	@Column(name = "phone")
	private String phone;
	@Column(name = "dob")
	private String date_of_birth;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Account> accounts;

	public Customer() {
	}

	public Customer(String firstName, String lastName, String address, String city, String email, String date_of_birth,
			String country, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.email = email;
		this.date_of_birth = date_of_birth;
		this.country = country;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", city=" + city + ", country=" + country + ", phone=" + phone
				+ ", date_of_birth=" + date_of_birth + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		if (accounts == null)
			accounts = new ArrayList<Account>();
		accounts.add(account);
	}

	public Account getDefualtAccount() {
		if (accounts != null)
			return accounts.get(0);
		return null;
	}

	public boolean isOwener(Account trackedAccount) {
		if (accounts == null)
			return false;
		for (Account account : accounts) {
			if (account.getAccountNumber() == trackedAccount.getAccountNumber())
				return true;
		}
		return false;
	}

}