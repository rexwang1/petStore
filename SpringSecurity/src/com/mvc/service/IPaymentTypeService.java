package com.mvc.service;

import java.util.List;

import com.mvc.entity.PaymentType;

public interface IPaymentTypeService {

	public void addPaymentType(PaymentType paymentType);
	public void deletePaymenType(PaymentType paymentType);
	public void updatePaymenType(PaymentType paymentType);
	public List<PaymentType> findAll();
}
