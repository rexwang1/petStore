package com.mvc.security;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.mvc.entity.UserManager;

public interface IUserOperator {
	void changePassword(String username, String password);
	public boolean isValidOldPassword(UserDetails user,String checkedPassword);
	public void createUser(String username, String password, String email,String groupName);
	public boolean userLived(String username);
	public List<UserManager> findAllUser();
	public UserDetails loadUserByUsername(String username);
	public void updateUser(User user);
}
