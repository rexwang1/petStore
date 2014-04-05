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
	public void loadUserInfo(@RequestParam("username") String username,Model model){
		model.addAttribute("userInfo", userInfoService.findByUsername(username));
	}

	@RequestMapping(value="/userInfo/update.do",method= RequestMethod.POST)
	public String saveUserInfo(@ModelAttribute UserInfo userInfo){
		userInfoService.addUserInfo(userInfo);
		return "redirect:/home.do";
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
