package com.mvc.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Commoditiez;
import com.mvc.service.ICommoditiezService;

@Controller
public class CommoditiezController extends BaseController{
	
	@Autowired
	private ICommoditiezService commoditiezService;

	
	@RequestMapping(value="/commoditiez/home.do",method=RequestMethod.GET)
	public void home(@RequestParam("type") String type,Model model){
		model.addAttribute("commoditiezs", commoditiezService.getCommodByType(type));
	}
	
	//only for Admin
	@RequestMapping(value="/commodtiez/addCommod.do",method=RequestMethod.GET)
	public void addCommod(){
		
	}
	
	//only for Admin
	@RequestMapping(value="/commoditiez/updateCommod.do",method=RequestMethod.GET)
	public void upadateCommod(@RequestParam("id") long id,Model model){
		model.addAttribute("commoditez", commoditiezService.findCommod(id));
	}
	
	//only for Admin
	@RequestMapping(value="/commodtiez/saveCommod.do",method=RequestMethod.POST)
	public String saveCommod(@ModelAttribute Commoditiez commoditiez){
		commoditiezService.addCommoditiez(commoditiez);
		return "redirect:/commoditiez/home.do";
	}
	
	@RequestMapping(value="/commodities/checkInvenNum.do",method=RequestMethod.GET)
	public void checkInventNum(@RequestParam("id") long id,@RequestParam("num") long num,HttpServletResponse response) throws IOException{
		Commoditiez commoditiez = commoditiezService.findCommod(id);
		long amount = commoditiez.getInventory().getAmount();
		
		if(amount < num){
			response.getWriter().print("{\"valid\":\"false\"}");
		}else{
			response.getWriter().print("{\"valid\":\"true\"}");
		}
	}
	
	
	public ICommoditiezService getCommoditiezService() {
		return commoditiezService;
	}

	public void setCommoditiezService(ICommoditiezService commoditiezService) {
		this.commoditiezService = commoditiezService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormate =new SimpleDateFormat("yyyy-MM-dd");
		dateFormate.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormate, true));
	}
}
