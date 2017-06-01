/**
 * 
 */
package com.hbourras.kata.bankaccount.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hbourras.kata.bankaccount.bean.AccountBean;
import com.hbourras.kata.bankaccount.bean.OperationBean;
import com.hbourras.kata.bankaccount.exception.BadAmountException;
import com.hbourras.kata.bankaccount.exception.NotEnoughAccountBalanceException;
import com.hbourras.kata.bankaccount.service.impl.AccountService;

/**
 * API Bank Account
 * 
 * @author Hamid
 */
@RestController
@RequestMapping("/api")
public class ApiRestController {

	private static Logger logger = Logger.getLogger(ApiRestController.class);

	@Autowired
	AccountService service;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = "application/json")
	public List<AccountBean> listAllAccounts() {
		return service.retrieveAllAccounts();
	}

	@RequestMapping(value = "/account/{accountNumber}/operations", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<OperationBean> listAllAccountOperations(@PathVariable("accountNumber") @NotEmpty String accountNumber) {
		List<OperationBean> operations = new ArrayList<OperationBean>();
		AccountBean account = service.retrieveAccountByNumber(accountNumber);
		if (account != null) {
			operations = account.getOperations();
		}
		return operations;
	}

	@RequestMapping(value = "/account/{accountNumber}/deposit", method = RequestMethod.PUT)
	public void addAccountDeposit(@PathVariable("accountNumber") @NotEmpty String accountNumber,
			@RequestParam @NotEmpty Double amount) throws BadAmountException {
		logger.info("Update an Account with a Deposit Operation "  + amount.toString());
		AccountBean account = service.retrieveAccountByNumber(accountNumber);
		if (account != null) {
			service.doDeposit(account, amount);
		}
	}

	@RequestMapping(value = "/account/{accountNumber}/withdrawl", method = RequestMethod.PUT)
	public void addAccountWithdrawl(@PathVariable("accountNumber") @NotEmpty String accountNumber,
			@RequestParam @NotEmpty Double amount) throws BadAmountException, NotEnoughAccountBalanceException {
		logger.info("Update an Account with a Withdrawl Operation " + amount.toString());
		AccountBean account = service.retrieveAccountByNumber(accountNumber);
		if (account != null) {
			service.doWithdrawl(account, amount);
		}
	}
}
