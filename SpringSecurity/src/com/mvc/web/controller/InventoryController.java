package com.mvc.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.entity.Inventory;
import com.mvc.service.IInventoryService;
import com.mvc.util.ResultFilter;

@Controller
public class InventoryController extends BaseController {
	
	@Autowired
	private IInventoryService inventoryService;
	
	
	@RequestMapping(value="/inventory/list.do")
	public ModelAndView loadAll(@ModelAttribute ResultFilter<Inventory> rf){
		inventoryService.listInventory(rf);
		return new ModelAndView("/inventory/list","rf",rf);
	}
	
	@RequestMapping(value="/inventory/addInventory.do",method=RequestMethod.GET)
	public void addInventory(){
		
	}
	
	//for Admin
	@RequestMapping(value="/inventory/addInventory.do",method=RequestMethod.POST)
	public String saveInventory(@ModelAttribute Inventory inventory){
		inventory.setPurchaseDate(new Date());
		inventoryService.addInventory(inventory);
		return "redirect:/inventory/list.do";
	}

	
	public IInventoryService getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(IInventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	
}
