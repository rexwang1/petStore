package com.mvc.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.BankCard;

@Service("bankCardService")
public class BankCardServiceImpl implements IBankCardService {

	@Autowired
	private ManagerDao managerDao;
	
	@Override
	public void addBankCard(BankCard bankCard) {
		// TODO Auto-generated method stub
		managerDao.add(bankCard);
	}

	@Override
	public void updateBankCard(BankCard bankCard) {
		// TODO Auto-generated method stub
		managerDao.update(bankCard);
	}

	@Override
	public void deleteBankCard(BankCard bankCard) {
		// TODO Auto-generated method stub
		managerDao.delete(bankCard);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankCard> findAll() {
		// TODO Auto-generated method stub
		return ((List<BankCard>) managerDao.findAll(BankCard.class.getSimpleName()));
	}

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public BankCard findCard(long cardNo, String name) {
		Query query = managerDao.getNameQuery("findCard");
		query.setLong("cardNo", cardNo);
		query.setString("name", name);
		
		return (BankCard) query.uniqueResult();
	}
	
	
}
