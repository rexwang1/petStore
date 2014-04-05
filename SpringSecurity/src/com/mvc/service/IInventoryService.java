package com.mvc.service;

import java.util.List;

import com.mvc.entity.Inventory;

public interface IInventoryService {

	public void addInventory(Inventory inventory);
	public void updateInventory(Inventory inventory);
	public void deleteInventory(Inventory inventory);
	public List<?> findAll();
}
