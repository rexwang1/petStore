package com.mvc.service;

import java.util.List;

import com.mvc.entity.ShopList;
import com.mvc.entity.UserInfo;

public interface IUserInfoService {
	
	public void addUserInfo(UserInfo user);
	public void deleteUserInfo(UserInfo userInfo);
	public List<UserInfo> findAll();
	public void updateUserInfo(UserInfo userInfo);
	public int getShopListCount();
	/* 
	 *
	 * @param currentPage 开始记录数
	 * @param numPage  页记录数
	 * @return 分页查询结果集
	 */
	public List<UserInfo> getUserInfoListForPage(int startIndex,int numPage);
	public UserInfo findByUsername(String username);
	
	

	
}
