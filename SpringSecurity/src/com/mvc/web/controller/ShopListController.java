package com.mvc.web.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.mvc.entity.PaymentType;
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
	
	

	protected final transient Log log = LogFactory.getLog(ShopListController.class);
//	@Autowired
//	private IUserInfoService userInfoService;
	
	@RequestMapping(value="/shopList/index.do")
	public ModelAndView loadAll(@ModelAttribute ResultFilter<ShopList> rf){
		shopListService.listShopList(rf);
		return new ModelAndView("/shopList/index.do","rf",rf);
	}
	
	
	
	//---------------清单操作--------------------------
	@RequestMapping(method = RequestMethod.GET,value="/shopList/addShopList.do")
	public void addShopList(Model model){
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/shopList/updateShopList.do")
	public void updateShopList(@RequestParam("id") long id,Model model){
		model.addAttribute("shopList", shopListService.getShopList(id));
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/shopList/updateShopList.do")
	public String updateShopList(@ModelAttribute ShopList shopList,@ModelAttribute PaymentType paymentType,
			@RequestParam("cardNo") long cardNO){
		shopList.setUserInfo(getUserInfo());
		shopListService.updateShopList(shopList, paymentType,cardNO);
		return "/shopList/index.do";
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/shopList/saveShopList.do")
	public String saveShopList(@ModelAttribute ShopList shoplist,@ModelAttribute PaymentType paytype){
		shoplist.setPaymentType(paytype);
		shopListService.addShopList(shoplist);
		return "/shopList/index.do";
	}
	
	@RequestMapping("/shopList/deleteShopList.do")
	public void deleteShopList(@RequestParam("id") long id){
		ShopList shopList = shopListService.findOne(id);
		
		shopListService.deleteShopList(shopList);
		
		
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
	public void saveCommod(@RequestParam("comid") long comid,@RequestParam("num") int num,
			HttpServletResponse response) throws IOException{
		//shopListService.updateShopList(shopList);
		
		
		Commoditiez commoditiez = commoditiezService.findCommod(comid);
		long id = shopListService.addShopCommoditiez(getUsername(),num, commoditiez);
		response.getWriter().print("{\"num\":\"" +
				id+"\"}");
		
	}
	
//	//购买商品
//	@RequestMapping(method = RequestMethod.POST,value="/shopList/saveCom.do")
//	public String saveCommod(@RequestParam("comid") long comid,@RequestParam("num") int num,
//			HttpServletResponse response) throws IOException{
//		//shopListService.updateShopList(shopList);
//		
//		
//		Commoditiez commoditiez = commoditiezService.findCommod(comid);
//		long id = shopListService.addShopCommoditiez(getUsername(),num, commoditiez);
//		response.getWriter().print("{\"num\":\"" +
//				id+"\"}");
//		return "/shopList/index.do";
//	}
	
	
	@RequestMapping(value="/shopList/deleteCom.do")
	public void delete(@RequestParam("id") long id,@RequestParam("comid") long comid,HttpServletResponse response){

		try {
			Commoditiez commoditiez =new Commoditiez();
			commoditiez.setItemNo(comid);
			shopListService.deleteShopCommodites(id, commoditiez);
			response.getWriter().print("{\"del\":\"true\"}");
		} catch (Exception e) {
			log.debug(e);
			e.printStackTrace();
		}
	}
	
	
//	@RequestMapping(value="/shopList/deleteShopList.do")
//	public String cancelCom(){
//		return "redirect:/shopList/index.do";
//	}
	
	@RequestMapping(value="/shopList/cancel.do")
	public String cancelCom(){
		return "redirect:/shopList/index.do";
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
		binder.registerCustomEditor(Long.class, new PropertyEditorSupport(){
			@Override
			public String getAsText() {

				return getAsText().toString();
			}
			
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				System.out.println("------------------------------");
				if(text == null || text.trim().equals("")){
					setValue(0L);
				}else{
					setValue(Long.parseLong(text));
				}
				
			}
		});
	}
	
}
