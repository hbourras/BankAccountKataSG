/**
 * 
 */
package com.hbourras.kata.bankaccount.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hbourras.kata.bankaccount.enumerator.CurrencyEnum;
import com.hbourras.kata.bankaccount.enumerator.OperationEnum;

/**
 * AccountBean represents a bank account with an account number, the owner information,
 * the current balance, the currency applied and finally the operations
 * 
 * @author Hamid
 */
public class AccountBean {
	
	private String number;
	
	private String owner;
	
	private Double balance; 
	
	private CurrencyEnum currency ;
	
	private List<OperationBean> operations;

	public AccountBean(String number, String owner, Double balance, CurrencyEnum currency) {
		super();
		this.number = number;
		this.owner = owner;
		this.balance = balance;
		this.currency = currency;
		this.operations = new ArrayList<OperationBean>();
		this.operations.add(new OperationBean(Calendar.getInstance().getTime(),balance,OperationEnum.DEPOSIT));
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public List<OperationBean> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationBean> operations) {
		this.operations = operations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountBean other = (AccountBean) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}
