package com.mvc.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
public class BaseController {

	protected Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@ModelAttribute("showLoginLink")
	public boolean getShowLoginLink(){
		for(GrantedAuthority authority:getAuthentication().getAuthorities()){
			if(authority.getAuthority().equals("ROLE_USER")){
				return false;
			}
		}
		return true;
	}
	
	@Autowired
	SessionRegistry sessionRegistry;
	
	@ModelAttribute("numUsers")
	public int getNumberOfUsers(){
		
		return sessionRegistry.getAllPrincipals().size();
	}
	
	protected void setMessage(HttpServletRequest request, String message) {
		request.getSession(true).setAttribute("GLOBAL_MESSAGE", message);
	}
	
	@ModelAttribute("gobalMessage")
	public String getMessage(HttpServletRequest request){
		final String message = (String)request.getSession(true).getAttribute("GLOBAL_MESSAGE");
		request.getSession().removeAttribute("GLOBAL_MESSAGE");
		return message;
	}
	
	//得到用户名
	protected String getUsername(){
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=principal.toString();
		if(principal instanceof UserDetails){
			username=((UserDetails)principal).getUsername();
		}
		
		return username;
	}
}
