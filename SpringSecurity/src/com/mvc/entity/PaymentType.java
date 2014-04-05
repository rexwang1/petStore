package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(catalog="petstore",name="payment_type")
public class PaymentType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private long shopListID;
	private int typeID;
	private BankCard card;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getShopListID() {
		return shopListID;
	}
	public void setShopListID(long shopListID) {
		this.shopListID = shopListID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	@OneToOne(cascade={CascadeType.ALL})
	public BankCard getCard() {
		return card;
	}
	public void setCard(BankCard card) {
		this.card = card;
	}
	
	
	
}
