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
//import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.UserInfo;
import com.mvc.service.IUserInfoService;

@Controller
public class UserInfoController extends BaseController{

	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping(value="/userInfo/home.do",method= RequestMethod.GET)
	public void home(){
		
	}
	
	
	@RequestMapping(value="/userInfo/updateInfo.do",method= RequestMethod.GET)
	public void loadUserInfo(Model model){
		model.addAttribute("userInfo", userInfoService.findByUsername(getUsername()));
	}
	
	@RequestMapping(value="/userInfo/update.do",method= RequestMethod.POST)
	public String saveUserInfo(@ModelAttribute UserInfo userInfo){
		userInfoService.addUserInfo(userInfo);
		return "redirect:/home.do";
	}
	
	//判断登陆的角色是否已有个人信息
	@RequestMapping(value="/userInfo/check.do")
	public void checkShopInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		UserInfo userInfo = userInfoService.findByUsername(getUsername());
		
		if(userInfo == null){
			response.getWriter().print("{\"hasInfo\":\"false\"}");
			this.setMessage(request, "你还没有完善个人信息");
		}
	}
	
	public IUserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormate =new SimpleDateFormat("yyyy-MM-dd");
		dateFormate.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormate, true));
	}
}
