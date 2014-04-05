package com.mvc.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.mvc.service.IUserService;

@Controller
public class UserManagerController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(method=RequestMethod.GET,value="/userManager/index.do")
	public String loadUsers(Model model){
		List<User> users = userService.findAllUser();
		model.addAttribute("users", users);
		return "/userManager/index.do";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET,value="/userManager/update.do")
	public String updateUsers(@RequestParam("username")String username,Model model){
		
		model.addAttribute("user", userService.getUser(username));
		return "/userManager/update.do";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/userManager/update.do")
	public String commitUpdate(@ModelAttribute User user){
		
		userService.updateUser(user);
		return "/userManager/index.do";
	}
	
	
	
}
