package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.PaymentType;

@Service("paymentTypeService")
public class PaymentTypeServiceImpl implements IPaymentTypeService {

	@Autowired
	private ManagerDao managerDao;
	
	@Override
	public void addPaymentType(PaymentType paymentType) {
		// TODO Auto-generated method stub
		managerDao.add(paymentType);
	}

	@Override
	public void deletePaymenType(PaymentType paymentType) {
		// TODO Auto-generated method stub
		managerDao.delete(paymentType);
	}

	@Override
	public void updatePaymenType(PaymentType paymentType) {
		// TODO Auto-generated method stub
		managerDao.update(paymentType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentType> findAll() {
		// TODO Auto-generated method stub
		return (List<PaymentType>) managerDao.findAll(PaymentType.class.getSimpleName());
	}

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	
}
