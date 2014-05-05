package com.mvc.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.mvc.entity.Commoditiez;
import com.mvc.entity.CommodityCondition;
import com.mvc.service.ICommoditiezService;
import com.mvc.util.ResultFilter;

@Controller
public class CommoditiezController extends BaseController{
	
	@Autowired
	private ICommoditiezService commoditiezService;
	
	@RequestMapping(value="/commoditiez/list.do",method=RequestMethod.GET)
	public void home(@RequestParam("type") String type,Model model){
		
		
		
		CommodityCondition condition = new CommodityCondition();
		ResultFilter<Commoditiez> rf = new ResultFilter<Commoditiez>();
		condition.setType(type);
		commoditiezService.listCommodites(condition, rf);
		model.addAttribute("rf", rf);
		
	}
	
	@RequestMapping(value="/commoditiez/list.do")
	public ModelAndView loadAll(@ModelAttribute CommodityCondition condition,@ModelAttribute ResultFilter<Commoditiez> rf){
		commoditiezService.listCommodites(condition, rf);
		return new ModelAndView("/commoditiez/list","rf",rf);
	}
	
	
	@RequestMapping(value="/commoditiez/detail.do",method=RequestMethod.GET)
	public String detail(@RequestParam("id") long itemNo,Model model,HttpServletRequest request){
		if(request.getUserPrincipal() == null){
			this.setMessage(request, "NO_LOGIN");
		}
		
		model.addAttribute("commodities", commoditiezService.findCommod(itemNo));
		return null;
	}
	
	//only for Admin
	@RequestMapping(value="/commoditiez/addCommod.do",method=RequestMethod.GET)
	public void addCommod(@RequestParam("id") long id,Model model){
		model.addAttribute("id", id);
	}
	
	//only for Admin
	@RequestMapping(value="/commoditiez/updateCommod.do",method=RequestMethod.GET)
	public void upadateCommod(@RequestParam("id") long id,Model model){
		model.addAttribute("commoditez", commoditiezService.findCommod(id));
	}
	
	//only for Admin
	@RequestMapping(value="/commoditiez/saveCommod.do",method=RequestMethod.POST)
	public String saveCommod(@RequestParam("id") long id,@ModelAttribute Commoditiez commoditiez){
	
		
		commoditiezService.addCommoditiez(id,commoditiez);
		return "redirect:/commoditiez/list.do";
	}
	
	
	
	@RequestMapping(value="/commoditiez/checkInvenNum.do")
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
