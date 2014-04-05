package com.mvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.Commoditiez;
import com.mvc.entity.PaymentType;
import com.mvc.entity.ShopList;
import com.mvc.entity.UserInfo;
import com.mvc.util.ResultFilter;

@Service("shopListService")
public class ShopListServiceImpl implements IShopListService{

	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Override
	public void addShopList(ShopList shopList) {
		// TODO Auto-generated method stub
		
		managerDao.add(shopList);
	}

	@Override
	public void updateShopList(ShopList shopList) {
		// TODO Auto-generated method stub
		managerDao.update(shopList);
	}

	@Override
	public void deleteShopList(ShopList shopList) {
		// TODO Auto-generated method stub
		managerDao.delete(shopList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopList> findAll() {
		// TODO Auto-generated method stub
		return (List<ShopList>) managerDao.findAll(ShopList.class.getSimpleName());
	}

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public int getShopListCount() {
		// TODO Auto-generated method stub
		return managerDao.getCount(ShopList.class.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopList> getShopListForPage(int startIndex, int numPage) {
		// TODO Auto-generated method stub
		return (List<ShopList>) managerDao.getListForPage(ShopList.class.getSimpleName(), startIndex, numPage);
	}

	@Override
	public void listShopList(ResultFilter<ShopList> rf) {
		// TODO Auto-generated method stub
		rf.setTotalCount(getShopListCount());
		rf.setItems(getShopListForPage((rf.getCurrentPage()-1) * rf.getPageSize(),
				rf.getPageSize()));
	}

	@Override
	public ShopList getShopList(long id) {
		// TODO Auto-generated method stub
		return (ShopList) managerDao.findOne(ShopList.class, id);
	}

	@Override
	public void addShopCommoditiez(String username,Commoditiez commodites) {
		// TODO Auto-generated method stub
		ShopList shopList = this.findSuitShopList(username, 1);
		
		List<Commoditiez> commoditesList = shopList.getCommoditiezs();
		
		if(commoditesList == null){
			commoditesList = new ArrayList<Commoditiez>();
		}
		commoditesList.add(commodites);
		managerDao.update(shopList);
	}

	@Override
	public void deleteShopCommodites(long id,Commoditiez commoditez) {
		// TODO Auto-generated method stub
		ShopList shopList = (ShopList) managerDao.findOne(ShopList.class, id);
		shopList.getCommoditiezs().remove(commoditez);
	}

	@Override
	public void addPayType(long id,PaymentType payType) {
		// TODO Auto-generated method stub
		ShopList shopList = (ShopList) managerDao.findOne(ShopList.class, id);
		shopList.setPaymentType(payType);
	}

	@Override
	public int getCommoditiezCount(long shopId) {
		// TODO Auto-generated method stub
		return managerDao.getCount(Commoditiez.class.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commoditiez> getCommoditiezForPage(long id, int startIndex,
			int numPage) {
		
		Criteria shopCrit = managerDao.getSessionFactory().getCurrentSession()
				.createCriteria(ShopList.class);
		shopCrit.add(Restrictions.eq("id", id));
		
		Criteria commodCrit = shopCrit.createCriteria("commoditiezs");
		commodCrit.setFirstResult(startIndex);
		commodCrit.setMaxResults(numPage);
		return commodCrit.list();
	}

	@Override
	public void listCommodites(long id,ResultFilter<Commoditiez> rf) {
		rf.setTotalCount(getCommoditiezCount(id));
		rf.setItems(getCommoditiezForPage(id, (rf.getCurrentPage()-1) * rf.getPageSize(),
				rf.getPageSize()));
	}

	
	@Override
	public ShopList createNewShopList(String username) {
		UserInfo userInfo = userInfoService.findByUsername(username);
		ShopList shopList = new ShopList();
		
		shopList.setAddressee(userInfo.getUsername());
		shopList.setConsigneeAddress(userInfo.getAddress());
		shopList.setCreateDate(new Date());
		shopList.setUserInfo(userInfo);
		shopList.setStatus(1);
		
		this.addShopList(shopList);
		
		return shopList;
	}

	@Override
	public ShopList getSuitShopList(String username, int status) {
		ShopList shopList = this.findSuitShopList(username, status);
		
		if(shopList == null){
			shopList = this.createNewShopList(username);
		}
		
		return shopList;
	}

	@Override
	public ShopList findSuitShopList(String username, int status) {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoService.findByUsername(username);
		
		Query query = managerDao.getNameQuery("findBySuit");
		query.setEntity("userInfo", userInfo);
		query.setInteger("status", 1);
		query.setMaxResults(1);
		
		@SuppressWarnings("unchecked")
		List<ShopList> shopLists = query.list();
		
		if(shopLists == null || shopLists.isEmpty()){
			return null;
		}
		return shopLists.get(0);
	}

	public IUserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	
	
}
