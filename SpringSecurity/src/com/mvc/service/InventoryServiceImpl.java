package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.Inventory;

@Service("inventoryService")
public class InventoryServiceImpl implements IInventoryService{

	@Autowired
	private ManagerDao managerDao;

	@Override
	public void addInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		managerDao.add(inventory);
	}

	@Override
	public void updateInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
		managerDao.update(inventory);
	}

	@Override
	public void deleteInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		managerDao.delete(inventory);
	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return managerDao.findAll(Inventory.class.getSimpleName());
	}
	
	
	
}
