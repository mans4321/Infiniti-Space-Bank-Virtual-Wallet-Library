package com.infiniti_space_bank.virtual_wallet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.exception.NoSuchCustomer;
import com.infiniti_space_bank.virtual_wallet.spring.config.AppConfig;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.service.WalletService;
import com.infiniti_space_bank.virtual_wallet.utility.Wallet;

import junit.framework.TestCase;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConcurrentAccountTransactions extends TestCase {

	@Autowired
	private WalletService walletService;
	private Wallet wallet;
	private double initBalance;
	private Thread depositThread;
	private Thread withdrawThread;

	@Before
	public void setUp() throws NoSuchCustomer {
		Customer customer = walletService.getCustomer(1);
		wallet = new Wallet(customer, walletService);
		initBalance = customer.getDefualtAccount().getBalance();

		depositThread = new Thread() {
			public void run() {
				wallet.deposit(initBalance / 10);
			}
		};

		withdrawThread = new Thread() {
			public void run() {
				try {
					wallet.withdraw(initBalance / 10);
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeposit() throws NoSuchCustomer {
		for (int i = 0; i < 5; i++) {
			depositThread.run();
			withdrawThread.run();
		}
		assertEquals(initBalance, walletService.getCustomer(1).getDefualtAccount().getBalance(), .001);
	}
}
