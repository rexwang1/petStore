package com.mvc.service;

import java.util.List;

import com.mvc.entity.Commoditiez;
import com.mvc.entity.PaymentType;
import com.mvc.entity.ShopList;
import com.mvc.util.ResultFilter;

public interface IShopListService {

	public void addShopList(ShopList shopList);
	public void updateShopList(ShopList shopList);
	public void deleteShopList(ShopList shopList);
	
	public List<ShopList> findAll();
	public int getShopListCount();
	public int getCommoditiezCount(long shopId);
	
	public void addShopCommoditiez(String username,Commoditiez commodites);
	public void deleteShopCommodites(long id,Commoditiez commodites);
	
	public void addPayType(long id,PaymentType payType);
	
	/* 
	 *
	 * @param currentPage 开始记录数
	 * @param numPage  页记录数
	 * @return 分页查询结果集
	 */
	public List<ShopList> getShopListForPage(int startIndex,int numPage);
	public void listShopList(ResultFilter<ShopList> rf);
	public ShopList getShopList(long id);
	
	public List<Commoditiez> getCommoditiezForPage(long id,int startIndex,int numPage);
	public void listCommodites(long id,ResultFilter<Commoditiez> rf);
	
	

	/**
	 * 寻找合适的清单 根据用户名、状态来找
	 * @param username 用户名
	 * @param status   状态
	 * @return		   适合的清单
	 */
	public ShopList findSuitShopList(String username,int status);
	
	/**
	 * 对于无带购的清单的情况下
	 * 创建新的清单
	 * @param username  用户名
	 * @return 
	 */
	public ShopList createNewShopList(String username);
	
	/**
	 * 得到合适的清单 根据用户名、状态条件
	 * @param username 用户名
	 * @param status   状态
	 * @return		   适合的清单
	 */
	public ShopList getSuitShopList(String username,int status);
	
}
