package com.infiniti_space_bank.virtual_wallet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTransactions.class, ConcurrentAccountTransactions.class })
public class AllTests {

}
