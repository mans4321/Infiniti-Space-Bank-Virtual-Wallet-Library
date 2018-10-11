package com.infiniti_space_bank.virtual_wallet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infiniti_space_bank.virtual_wallet.exception.CustomerIsNotAccountOwenr;
import com.infiniti_space_bank.virtual_wallet.exception.InsufficientBalanceException;
import com.infiniti_space_bank.virtual_wallet.exception.NoSuchCustomer;
import com.infiniti_space_bank.virtual_wallet.spring.config.AppConfig;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Account;
import com.infiniti_space_bank.virtual_wallet.spring.entity.Customer;
import com.infiniti_space_bank.virtual_wallet.spring.service.WalletService;
import com.infiniti_space_bank.virtual_wallet.utility.Wallet;

import junit.framework.TestCase;


@ContextConfiguration(classes=AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountTransactions extends TestCase {
    
	   @Autowired
	    private WalletService walletService;
	   	private Wallet wallet;
	   	private double initBalance ;
    
	   @Before 
	   public void setUp() throws NoSuchCustomer{
		   Customer customer = walletService.getCustomer(1);
		   wallet = new Wallet(customer, walletService);
		   initBalance = customer.getDefualtAccount().getBalance();
	   }
	   
	   @After 
	   public void tearDown() throws NoSuchCustomer{
		   wallet = null;
		   initBalance = 0;
	   }
	   
	    @Test
	    @Transactional
	    @Rollback(true)
	    public void testDeposit() throws NoSuchCustomer
	    {
	    	 wallet.deposit(20.80);
	   	     assertEquals(initBalance + 20.80, walletService.getCustomer(1).getDefualtAccount().getBalance(), .001);
	    }

	    @Test
	    @Transactional
	    @Rollback(true)
	    public void testWithdraw() throws InsufficientBalanceException, NoSuchCustomer
	    {
	    	 wallet.withdraw(20.80);
	   	     assertEquals(initBalance - 20.80 , walletService.getCustomer(1).getDefualtAccount().getBalance(), .001);
	    }

	    
	    @Test(expected = InsufficientBalanceException.class)
	    @Transactional
	    @Rollback(true)
	    public void testWithdrawException() throws InsufficientBalanceException
	    {
	    	 wallet.withdraw(initBalance + 20.80);
	    }
	    
	    @Test(expected = NoSuchCustomer.class)
	    @Transactional
	    @Rollback(true)
	    public void testNoSuchCustomer() throws NoSuchCustomer  
	    {
	    	walletService.getCustomer(10000);
	    }
	    
	    @Test(expected = CustomerIsNotAccountOwenr.class)
	    @Transactional
	    @Rollback(true)
	    public void testCustomerIsNotAccountOwenr() throws CustomerIsNotAccountOwenr  
	    {
	    	Account account = new Account();
	    	account.setAccountNumber(2);
	    	wallet.setTrackedAccount(account);
	    }
	    
	    
	    
}
