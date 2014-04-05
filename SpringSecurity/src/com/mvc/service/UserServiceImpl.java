package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mvc.security.IUserOperator;

@Service("userService")
public class UserServiceImpl implements IUserService {

	
	@Autowired
	IUserOperator userOperator;
	
	private final static String GROUP_NAME="Users";
	
	@Override
	
	public void changePassword(String username, String password) {
		// TODO Auto-generated method stub
		userOperator.changePassword(username, password);
	}

	@Override
	public void createUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		userOperator.createUser(username, password, email,GROUP_NAME);
	}

	
	@Override
	//获取用户权限表的用户信息，这里主要针对管理员
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return (User) userOperator.loadUserByUsername(username);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		System.err.println((User) userOperator.loadUserByUsername("guest"));
		return null;
//				jdbcDao.findAllUser();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userOperator.updateUser(user);
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return userOperator.userLived(username);
	}

	

	
}
