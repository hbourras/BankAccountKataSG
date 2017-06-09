/**
 * 
 */
package com.hbourras.kata.bankaccount.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hbourras.kata.bankaccount.bean.AccountBean;
import com.hbourras.kata.bankaccount.bean.OperationBean;
import com.hbourras.kata.bankaccount.enumerator.CurrencyEnum;
import com.hbourras.kata.bankaccount.enumerator.OperationEnum;
import com.hbourras.kata.bankaccount.exception.BadAmountException;
import com.hbourras.kata.bankaccount.exception.NotEnoughAccountBalanceException;
import com.hbourras.kata.bankaccount.service.impl.AccountService;

/**
 * Unit Tests
 * @author Hamid
 */
public class AccountServiceTest {
	
	private AccountService service;
	private AccountBean account;

	@Before
	public void setUp() throws Exception {
		account = new AccountBean("00001", "Hamid BOURRAS",new Double(500), CurrencyEnum.EUR);
		service = new AccountService();
	}

	/**
	 * Test if the account used in test is not null
	 */
	@Test
	public void testAccountBean() {
		assertNotNull(this.account);
	}
	
	/**
	 * Test the operation of deposit
	 */
	@Test
	public void testDeposit() {
		try {
			// perform a deposit
			service.doDeposit(account, new Double(100));
			// check the account balance 
			Assert.assertEquals(new Double(600), account.getBalance());
		} catch(Exception e) {
			// if anything wrong happened
			fail("Exception reached");
		}
	}
	
	/**
	 * Test the operation of withdrawl
	 */
	@Test
	public void testWithdrawl() {
		try {
			// perform a withdrawl
			service.doWithdrawl(account, new Double(100));
			// check the account balance 
			Assert.assertEquals(400, account.getBalance().intValue());
		} catch(Exception e) {
			// if anything wrong happened
			fail("Exception reached");
		}
	}
	
	/**
	 * Test on the operation's amount
	 */
	@Test
	public void testWithdrawlWithWrongAmount() {
		try {
			// perform a withdrawl with a negative amount
			service.doWithdrawl(account, new Double(-100));
			// next line should not be reached, test in failure
			fail("Should not be reached caused to wrong amount");
		} catch(BadAmountException e) {
			// correct exception caught
			assertTrue(true);
		} catch(NotEnoughAccountBalanceException e) {
			// wrong exception caught, test in failure
			fail("Should not be reached caused to wrong amount");
		}
	}
	
	/**
	 * Test on account's balance
	 */
	@Test
	public void testWithdrawlWithNotEnoughBalance() {
		try {
			// perform a withdrawl, with a higher amount than the account balance 
			service.doWithdrawl(account, new Double(600));
			// next line should not be reached, test in failure
			fail("Should not be reached caused to not enough balance");
		} catch(NotEnoughAccountBalanceException e) {
			// correct exception caught
			assertTrue(true);
		} catch(BadAmountException e) {
			// wrong exception caught, test in failure
			fail("Should not be reached caused to NotEnoughAccountBalanceException");
		}
	}
	
	/**
	 * Test on account's operations history when the account is created
	 */
	@Test
	public void testAccountWithNoOperation() {
		// by default, one operation linked to the account creation
		assertEquals(1,service.getOperationsHistory(account).size());
	}
	
	/**
	 * Test on account's operations history when a deposit is done 
	 */
	@Test
	public void testAccountWithDepositOperation() {
		try {
			// perform a deposit
			service.doDeposit(account, new Double(100));
			// retrieve all operations
			List<OperationBean> listOperations = service.getOperationsHistory(account);
			// test on operations count : 2
			// one at the creation, second one at the last operation of deposit
			assertEquals(2,listOperations.size());
			// check the last operation type
			assertTrue(listOperations.get(listOperations.size()-1).getType() == OperationEnum.DEPOSIT);
		} catch(Exception e) {
			// if anything wrong happened
			fail("Exception throwned");
		}
	}
	
	/**
	 * Test on account's operations history when a withdrawl is done 
	 */
	@Test
	public void testAccountWithWithdrawlOperation() {
		try {
			// perform a withdrawl
			service.doWithdrawl(account, new Double(100));
			// retrieve all operations
			List<OperationBean> listOperations = service.getOperationsHistory(account);
			// test on operations count : 2
			// one at the creation, second one at the last operation of deposit
			assertEquals(2,listOperations.size());
			// check the last operation type
			assertTrue(listOperations.get(listOperations.size()-1).getType() == OperationEnum.WITHDRAWL);
		} catch(Exception e) {
			// if anything wrong happened
			fail("Exception throwned");
		}
	}
}