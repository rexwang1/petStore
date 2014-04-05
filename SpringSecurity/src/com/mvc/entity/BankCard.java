package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog="petstore",name="bankcard")
public class BankCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private long cardNo;
	private String name;
	private double cash;
	private String subbranch;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public String getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}
	
	
}
