package com.hbourras.kata.bankaccount.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbourras.kata.bankaccount.bean.AccountBean;
import com.hbourras.kata.bankaccount.bean.OperationBean;
import com.hbourras.kata.bankaccount.dao.AccountDaoMock;
import com.hbourras.kata.bankaccount.enumerator.OperationEnum;
import com.hbourras.kata.bankaccount.exception.BadAmountException;
import com.hbourras.kata.bankaccount.exception.NotEnoughAccountBalanceException;

/**
 * Service related to the Bank Account Management
 * @author Hamid
 */
@Service
public class AccountService {
	
	@Autowired
	AccountDaoMock accountDao;
	
	/**
	 * Perform a deposit operation on an account
	 * @param account Account targeted  by the operation
	 * @param amount Amount of the operation
	 * @throws BadAmountException
	 */
	public void doDeposit(AccountBean account, final Double amount) throws BadAmountException {
		checkAmount(amount);
		account.setBalance(account.getBalance()+ amount);
		saveOperation(account,OperationEnum.DEPOSIT,amount);
	}

	/**
	 * Perform a withdrawl operation on an account
	 * @param account Account targeted  by the operation
	 * @param amount Amount of the operation
	 * @throws BadAmountException
	 * @throws NotEnoughAccountBalanceException
	 */
	public void doWithdrawl(AccountBean account, final Double amount) throws BadAmountException, NotEnoughAccountBalanceException {
		checkAmount(amount);
		if(account.getBalance() < amount) {
			throw new NotEnoughAccountBalanceException();
		}
		account.setBalance(account.getBalance() - amount);
		saveOperation(account,OperationEnum.WITHDRAWL,amount);
	}

	/**
	 * Retrieve all operations done on an account
	 * @param account Account in target
	 * @return list of operation related to the account
	 */
	public List<OperationBean> getOperationsHistory(AccountBean account) {
		return account.getOperations();
	}
	
	/**
	 * Save an operation on an account
	 * @param account Account targeted
	 * @param operation type of operation, can be a deposit or a withdrawl
	 * @param amount amount of the operation
	 */
	public void saveOperation(AccountBean account, OperationEnum operation, Double amount) {
		account.getOperations().add(new OperationBean(Calendar.getInstance().getTime(),amount,operation));
	}
	
	/**
	 * Retrieve all account
	 * @return
	 */
	public List<AccountBean> retrieveAllAccounts() {
		return accountDao.findAll();
	}
	
	/**
	 * Retrieve an account by its number
	 * @return
	 */
	public AccountBean retrieveAccountByNumber(String accountNumber) {
		return accountDao.findByAccountNumber(accountNumber);
	}

	/**
	 * Check if the amount is non-null and positive 
	 * @param amount amount
	 * @throws BadAmountException
	 */
	private void checkAmount(Double amount) throws BadAmountException {
		if(amount == null || amount <= 0 ) {
			throw new BadAmountException();
		}
	}
}
