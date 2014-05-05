package com.mvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.BankCard;
import com.mvc.entity.Commoditiez;
import com.mvc.entity.Inventory;
import com.mvc.entity.PaymentType;
import com.mvc.entity.ShopList;
import com.mvc.entity.ShopListLog;
import com.mvc.entity.ShopListLogKey;
import com.mvc.entity.UserInfo;
import com.mvc.util.ResultFilter;

@Service("shopListService")
public class ShopListServiceImpl implements IShopListService{

	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private IUserInfoService userInfoService;
	
//	@Autowired
//	private IShopListLogService shopListLogService;
//	
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
		
		String username = shopList.getUserInfo().getUsername();
		List<Commoditiez> commodtiezs = shopList.getCommoditiezs();
		if(commodtiezs != null){
			ShopListLogKey key = null;
			for(Commoditiez commod : commodtiezs)
			{
				key = new ShopListLogKey(shopList.getId(), commod.getItemNo(), 0);
				ShopListLog log = new ShopListLog();
				log.setShopListLogKey(key);
				log.setAmount(0);
				log.setUsername(username);
				managerDao.add(log);
			}
		}
		
		shopList.setUserInfo(null);
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

	@Transactional
	@Override
	public long addShopCommoditiez(String username,int num,Commoditiez commodites) {

		
		ShopList shopList = this.getSuitShopList(username, 1);
		ShopListLogKey logKey = new ShopListLogKey(shopList.getId(), commodites.getItemNo(), 1);
		
		ShopListLog log = (ShopListLog) managerDao.findOne(ShopListLog.class, logKey);
		
		if(log == null)
		{
			log = new ShopListLog();
			log.setAmount(num);
			log.setShopListLogKey(logKey);
			log.setUsername(username);
		}else{
			log.setAmount(log.getAmount() + num);
		}
		
		double comMoney = commodites.getPrice() * num;
		
		
		shopList.setAmount(shopList.getAmount() + num);
		shopList.setMoney(shopList.getMoney() + comMoney);
		
		List<Commoditiez> commoditesList = shopList.getCommoditiezs();
		
		if(commoditesList == null){
			commoditesList = new ArrayList<Commoditiez>();
		}
		
		Inventory inventory = commodites.getInventory();
		inventory.setAmount(inventory.getAmount() - num);
		
		commoditesList.add(commodites);
		
		shopList.setCommoditiezs(commoditesList);
		
		//增加日志记录
		log.setShopListLogKey(new ShopListLogKey(shopList.getId(), commodites.getItemNo(), 1));
		log.setAmount(num);
		log.setUsername(username);
		
		managerDao.update(inventory);
		managerDao.add(shopList);
		managerDao.add(log);
		
		return shopList.getId();
	}

	@Transactional
	@Override
	public void deleteShopCommodites(long id,Commoditiez commoditez) {
		// TODO Auto-generated method stub
		ShopList shopList = (ShopList) managerDao.findOne(ShopList.class, id);
		List<Commoditiez> commods = shopList.getCommoditiezs();
		Commoditiez removeCommod = new Commoditiez();
		for(int i=0; i<commods.size();i++){
			if(commods.get(i).getItemNo() == commoditez.getItemNo()){
				removeCommod = commods.remove(i);
				break;
			}
		}
		
		
		shopList.setCommoditiezs(commods);
		
		ShopListLogKey key = new ShopListLogKey();
		key.setShopId(id);
		key.setComId(removeCommod.getItemNo());
		key.setStatus(1);
		
		ShopListLog log = (ShopListLog) managerDao.findOne(ShopListLog.class, key);
		
		Inventory inventory = removeCommod.getInventory();
		inventory.setAmount(inventory.getAmount() + log.getAmount());
		
		shopList.setAmount(shopList.getAmount() - log.getAmount());
		double money = shopList.getMoney() -log.getAmount() * removeCommod.getPrice();
		shopList.setMoney(money);
		
		//增加撤销状态的订单日志
		key.setStatus(0);
		log.setShopListLogKey(key);
		log.setAmount(0);
		managerDao.add(log);
		managerDao.update(inventory);
		managerDao.update(shopList);
		
	}

	@Override
	public void addPayType(long id,PaymentType payType) {
		// TODO Auto-generated method stub
		ShopList shopList = (ShopList) managerDao.findOne(ShopList.class, id);
		shopList.setPaymentType(payType);
		managerDao.update(shopList);
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
		
		if(userInfo == null)
		{
			return null;
		}
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

	@Override
	public ShopList findOne(long id) {
		ShopList shopList = (ShopList) managerDao.findOne(ShopList.class, id);
		return shopList;
	}

	@Override
	public void updateShopList(ShopList shopList, PaymentType payment,long cardNo) {
		if(cardNo > 0){
			BankCard card = (BankCard)managerDao.findOne(BankCard.class, cardNo);
			payment.setCard(card);
		}
		
		shopList.setPaymentType(payment);
		managerDao.update(shopList);
		
	}

	
	
}
