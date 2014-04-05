package com.mvc.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.mvc.entity.ShopList;
import com.mvc.service.ICommoditiezService;
import com.mvc.service.IShopListService;
import com.mvc.util.ResultFilter;

@Controller
public class ShopListController extends BaseController {

	@Autowired
	private IShopListService shopListService;
	
	@Autowired
	private ICommoditiezService commoditiezService;
	
	@RequestMapping(value="/shopList/index.do")
	public ModelAndView loadAll(@ModelAttribute ResultFilter<ShopList> rf){
		shopListService.listShopList(rf);
		return new ModelAndView("/shopList/index.do","rf",rf);
	}
	
	
	
	//---------------清单操作--------------------------
	@RequestMapping(method = RequestMethod.GET,value="/shopList/addShopList.do")
	public void addShopList(){
		
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/shopList/saveShopList.do")
	public String saveShopList(@ModelAttribute ShopList shoplist){
		shopListService.addShopList(shoplist);
		return "/shopList/index.do";
	}
	
	//实际将清单的status属性改为0
	@RequestMapping("/shopList/deleteShopList.do")
	public void deleteShopList(@RequestParam("id") String id){
		ShopList shopList = shopListService.getShopList(Long.valueOf(id));
		shopList.setStatus(0);
		shopListService.updateShopList(shopList);
	}
	
	//--------------------清单的商品操作----------
	
	/**
	 * 
	 * @param id  ----清单的id
	 * @param model
	 */
	@RequestMapping(method = RequestMethod.GET,value="/shopList/addCom.do")
	public void addCommod(@RequestParam("id") String id,Model model){
		model.addAttribute("id", id);
	
	}
	
	//购买商品
	@RequestMapping(method = RequestMethod.POST,value="/shopList/saveCom.do")
	public String saveCommod(@RequestParam("comid") String comid){
		//shopListService.updateShopList(shopList);
		Commoditiez commoditiez = commoditiezService.findCommod(Long.valueOf(comid));
		shopListService.addShopCommoditiez(getUsername(), commoditiez);
		return "/shopList/index.do";
	}
	
	@RequestMapping(value="/shopList/deleteCom.do")
	public void delete(@RequestParam("id") String id,@RequestParam("comid") String comid){
//		ShopList shopList = new ShopList();
//		shopList.setId(Long.valueOf(id));
//		shopListService.deleteShopList(shopList);
		Commoditiez commoditiez =new Commoditiez();
		commoditiez.setItemNo(Long.valueOf(comid));
		shopListService.deleteShopCommodites(Long.valueOf(id), commoditiez);
	}
	
	
	
	public IShopListService getShopListService() {
		return shopListService;
	}

	public void setShopListService(IShopListService shopListService) {
		this.shopListService = shopListService;
	}


	public ICommoditiezService getCommoditiezService() {
		return commoditiezService;
	}


	public void setCommoditiezService(ICommoditiezService commoditiezService) {
		this.commoditiezService = commoditiezService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormate.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormate, true));
	}
	
}
