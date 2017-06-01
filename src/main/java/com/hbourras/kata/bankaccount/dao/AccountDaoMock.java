package com.hbourras.kata.bankaccount.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hbourras.kata.bankaccount.bean.AccountBean;
import com.hbourras.kata.bankaccount.bean.OperationBean;
import com.hbourras.kata.bankaccount.enumerator.CurrencyEnum;
import com.hbourras.kata.bankaccount.enumerator.OperationEnum;

@Component
public class AccountDaoMock {
	private static Map<String,AccountBean> accounts = new HashMap<String,AccountBean>();
	static {
		accounts.put("00001", new AccountBean("00001","Hamid BOURRAS",new Double(120.35),CurrencyEnum.EUR));
		accounts.get("00001").getOperations().add(new OperationBean(Calendar.getInstance().getTime(),new Double(120.35),OperationEnum.DEPOSIT));
	}
	
	public List<AccountBean> findAll() {
		List<AccountBean> accounts = new ArrayList<AccountBean>();
		accounts.addAll(AccountDaoMock.accounts.values());
		return accounts;
	}
	
	public AccountBean findByAccountNumber(String accountNumber) {
		AccountBean account = null;
		if(AccountDaoMock.accounts.containsKey(accountNumber)) {
			account = AccountDaoMock.accounts.get(accountNumber);
		}
		return account;
	}
}
