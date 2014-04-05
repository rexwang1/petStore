package com.mvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.entity.Inventory;
import com.mvc.service.IInventoryService;

@Controller
public class InventoryController extends BaseController {
	
	private IInventoryService inventoryService;
	
	@RequestMapping(value="/inventory/addInventory.do",method=RequestMethod.GET)
	public void addInventory(){
		
	}
	
	//for Admin
	@RequestMapping(value="/inventory/addInventory.do",method=RequestMethod.POST)
	public String saveInventory(@ModelAttribute Inventory inventory){
		inventoryService.addInventory(inventory);
		return "";
	}

	
	public IInventoryService getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(IInventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	
}
