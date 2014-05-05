package com.mvc.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.UserInfo;
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private ManagerDao managerDao;
	
	@Override
	public void addUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		managerDao.add(user);
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		managerDao.delete(userInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return ((List<UserInfo>) managerDao.findAll(UserInfo.class.getSimpleName()));
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		managerDao.update(userInfo);
	}

	@Override
	public int getShopListCount() {
		// TODO Auto-generated method stub
		return managerDao.getCount(UserInfo.class.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getUserInfoListForPage(int startIndex, int numPage) {
		// TODO Auto-generated method stub
		return (List<UserInfo>) 
				managerDao.getListForPage(UserInfo.class.getSimpleName(), startIndex, numPage);
	}

	@Override
	public UserInfo findByUsername(String username) {
		// TODO Auto-generated method stub
		Query query = managerDao.getNameQuery("findUserInfoByUserName");
		query.setString("userInfo", username);
		
		return (UserInfo) query.uniqueResult();
//		@SuppressWarnings("unchecked")
//		List<UserInfo> userInfos = query.list();
//		UserInfo userInfo = null;
//		if(!userInfos.isEmpty()){
//			userInfo =(UserInfo) userInfos.get(0);
//		}
//		return userInfo;
	}

	


}
