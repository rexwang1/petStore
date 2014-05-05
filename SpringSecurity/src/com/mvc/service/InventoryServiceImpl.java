package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.Inventory;
import com.mvc.util.ResultFilter;

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

	@Override
	public void listInventory(ResultFilter<Inventory> rs) {
		// TODO Auto-generated method stub
		rs.setTotalCount(getInventoryCount());
		rs.setItems(getInventoryForPage((rs.getCurrentPage()-1)*rs.getPageSize(), rs.getPageSize()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getInventoryForPage(int startIndex, int numPage) {
		// TODO Auto-generated method stub
		return (List<Inventory>) managerDao.getListForPage(Inventory.class.getSimpleName(),
				startIndex, numPage);
	}

	@Override
	public int getInventoryCount() {
		// TODO Auto-generated method stub
		return managerDao.getCount(Inventory.class.getSimpleName());
	}
	
	
	
}
