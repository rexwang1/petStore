package com.mvc.web.controller;

import java.util.List;

import javax.servlet.ServletResponse;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.UserManager;
import com.mvc.service.IUserRoleService;

@Controller
public class UserManagerController extends BaseController {

	@Autowired
	private IUserRoleService userRoleService;
	
	@RequestMapping(method=RequestMethod.GET,value="/userManager/index.do")
	public void loadUsers(Model model){
		List<UserManager> users = userRoleService.getUsers(); 
		model.addAttribute("userManagers", users);
		
	}
	
	
	
//	@RequestMapping(method = RequestMethod.GET,value="/userManager/update.do")
//	public String updateUsers(@RequestParam("username")String username,Model model){
//		
//		model.addAttribute("user", userRoleService.getUser(username));
//		return "/userManager/update.do";
//	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/userManager/update.do")
	public void commitUpdate(@RequestParam("username")String username,
			@RequestParam("groupName")String groupName){
		userRoleService.update(username, groupName);
		
	}
	
	
	@RequestMapping(value="/userManager/pullBack.do")
	public void pullBack(@RequestParam("username")String username,ServletResponse response){
		
		try {
			userRoleService.pullBackList(username);
			response.getWriter().print("{\"invalid\":\"true\"}");
			
		} catch (Exception e) {
			LogFactory.getLog(UserManagerController.class).debug(e);
			
		}
	}



	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}



	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	
	
}
