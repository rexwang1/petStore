package com.mvc.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.service.IUserService;

/**
 * This controller is used to provide functionality for the login page.
 * 
 * @author Mularien
 */


@Controller
public class LoginLogoutController extends BaseController{
	
	@Autowired
	private IUserService userService;

	
	@RequestMapping(method=RequestMethod.GET,value="/login.do")
	public void home() {
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/registration.do")
	public void registration(){
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/registration.do")
	public String submitRegistration(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email, HttpServletRequest request){
		if(userService.userExists(username)){
			setMessage(request,"The account with the username has used");
			return "redirect:registration.do";
		}
		userService.createUser(username, password, email);
		setMessage(request, "Your account has been created. Please log in.");
		return "redirect:home.do";		
	}
	
	// Ch 6 Access Denied
	@RequestMapping(method=RequestMethod.GET, value="/accessDenied.do")
	public void accessDenied(ModelMap model, HttpServletRequest request) {
		AccessDeniedException ex = (AccessDeniedException) request.getAttribute(AccessDeniedHandlerImpl.SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY);
		StringWriter sw = new StringWriter();
		model.addAttribute("errorDetails", ex.getMessage());
		ex.printStackTrace(new PrintWriter(sw));
		model.addAttribute("errorTrace", sw.toString());
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/account/changePassword.do")
	public void chanagePassword(){
		
	}
	@RequestMapping(method=RequestMethod.POST,value="/account/changePassword.do")
	public String savePassword(@RequestParam("passsword") String newPassword){
	
		userService.changePassword(getUsername(), newPassword);
				
				/*
				userDetailsManager.changePassword(oldPassword, newPassword);
				 */
		SecurityContextHolder.clearContext();
		return "redirect:home.do";
	}
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
}
