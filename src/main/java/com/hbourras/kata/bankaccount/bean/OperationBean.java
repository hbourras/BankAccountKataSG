package com.hbourras.kata.bankaccount.bean;

import java.util.Date;

import com.hbourras.kata.bankaccount.enumerator.OperationEnum;

/**
 * OperationBean object represents an operation of deposit/withdrawl on an account. 
 * 
 * An operation is qualified by its date, an amount and a type of operation which can be
 * a DEPOSIT or a WITHDRAWL
 * 
 * @author Hamid
 */
public class OperationBean implements Comparable<OperationBean>{
	
	private Date date;
	
	private Double amount;
	
	private OperationEnum type;
	
	public OperationBean() {
		super();
	}
	
	public OperationBean(Date date, Double amount, OperationEnum type) {
		this.date = date;
		this.amount = amount;
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public OperationEnum getType() {
		return type;
	}

	public void setType(OperationEnum type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OperationBean other = (OperationBean) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(OperationBean o) {
		return this.date.compareTo(o.getDate());
	}
	
	
}
