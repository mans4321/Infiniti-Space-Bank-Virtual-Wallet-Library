package com.infiniti_space_bank.virtual_wallet;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.infiniti_space_bank.virtual_wallet.api.VirtualWallet;
import com.infiniti_space_bank.virtual_wallet.api.VirtualWalletImpl;
import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.exception.NoSuchCustomer;
import com.infiniti_space_bank.virtual_wallet.spring.config.AppConfig;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Transaction;
import com.infiniti_space_bank.virtual_wallet.spring.service.WalletService;
import com.infiniti_space_bank.virtual_wallet.utility.Wallet;

public class MainApp {
	public static void main(String[] args) throws SQLException, NoSuchCustomer {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		WalletService walletService = (WalletService) context.getBean("walletService");
		VirtualWallet virtualWallet = new VirtualWalletImpl();
		Customer customer = virtualWallet.getCustomer(1);
		Wallet wallet = new Wallet(customer, walletService);

		System.out.println("");
		int userChoice = 0;
		double amount;
		int N;
		Scanner keyboard = new Scanner(System.in);
		String message;
		showMenu();

		while (true) {

			userChoice = (int) userInput(keyboard);

			System.out.println(userChoice);

			switch (userChoice) {
			case 1:
				message = "Creating a wallet for ";
				System.out.println(message);
				wallet = new Wallet(customer, walletService);
				message = "a wallet for user with id= 1 has been created ";
				System.out.println(message);
				showMenu();
				break;
			case 2:
				message = "please enter the amount to deposit  ";
				System.out.println(message);
				amount = userInput(keyboard);
				virtualWallet.deposit(wallet, amount);
				message = "account balance is " + wallet.getBalance();
				System.out.println(message);

				showMenu();
				break;
			case 3:
				message = "please enter the amount to withdraw ";
				System.out.println(message);
				amount = userInput(keyboard);
				try {
					virtualWallet.withdraw(wallet, amount);
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
				message = "account balance is " + wallet.getBalance();
				System.out.println(message);

				showMenu();
				break;
			case 4:
				message = "account balance is " + wallet.getBalance();
				System.out.println(message);
				virtualWallet.accountBalance(wallet);
				showMenu();
				break;
			case 5:
				message = "please enter the number of transactions";
				System.out.println(message);
				N = (int) userInput(keyboard);
				List<Transaction> transactions = virtualWallet.getTransaction(wallet, N);

				if (transactions == null) {
					message = "there are no associated with this account";
					System.out.println(message);
				} else {
					for (Transaction transaction : transactions) {
						System.out.println(transaction);
					}
				}

				showMenu();
				break;
			case 6:
				message = "please enter the customer id";
				System.out.println(message);
				int id = (int) userInput(keyboard);
				try {
					Customer newCustomer = virtualWallet.getCustomer(id);
					wallet = new Wallet(newCustomer, walletService);
				} catch (NoSuchCustomer e) {
					System.out.println(e.getMessage());
				}

				showMenu();
				break;
			case 0:
				System.out.println("Have a nice day!");
				keyboard.close();
				context.close();
				System.exit(0);
			default:
				System.out.println("Invalid Input, please try again.");
			}
		}

	}

	public static void showMenu() {
		System.out.println("\n****virtual wallet Lib****\n");
		System.out.println("Please select an option (1-6)");
		System.out.println("1. Create a wallet ");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Get Balance");
		System.out.println("5. Get N Transactions");
		System.out.println("6. Find A customer and apply transactions to her/his account ");
		System.out.println("0. Exit");
	}

	private static double userInput(Scanner keyboard) {
		double userChoice = 0;
		Boolean validAction = false;
		while (!validAction) {
			try {
				System.out.println("getting valid input");
				userChoice = keyboard.nextDouble();
				validAction = true;
			} catch (Exception e) {
				System.out.println("Invalid Input, please enter an Integer");
				validAction = false;
				keyboard.nextLine();
			}
		}
		return userChoice;
	}

}
