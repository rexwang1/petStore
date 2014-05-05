package com.mvc.service;

import java.util.List;

import com.mvc.entity.Inventory;
import com.mvc.util.ResultFilter;

public interface IInventoryService {

	public void addInventory(Inventory inventory);
	public void updateInventory(Inventory inventory);
	public void deleteInventory(Inventory inventory);
	public List<?> findAll();
	
	public void listInventory(ResultFilter<Inventory> rs);
	public List<Inventory> getInventoryForPage(int startIndex, int numPage);
	public int getInventoryCount();
}
