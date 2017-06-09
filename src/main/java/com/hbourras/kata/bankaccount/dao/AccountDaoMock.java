package com.hbourras.kata.bankaccount.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hbourras.kata.bankaccount.bean.AccountBean;
import com.hbourras.kata.bankaccount.enumerator.CurrencyEnum;

@Component
public class AccountDaoMock {
	private static Map<String,AccountBean> accounts = new HashMap<String,AccountBean>();
	static {
		accounts.put("00001", new AccountBean("00001","Hamid BOURRAS",new Double(100),CurrencyEnum.EUR));
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
