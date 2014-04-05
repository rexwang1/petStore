package com.mvc.service;

import java.util.List;

import com.mvc.entity.BankCard;

public interface IBankCardService {

	public void addBankCard(BankCard bankCard);
	public void updateBankCard(BankCard bankCard);
	public void deleteBankCard(BankCard bankCard);
	public List<BankCard> findAll();
}
