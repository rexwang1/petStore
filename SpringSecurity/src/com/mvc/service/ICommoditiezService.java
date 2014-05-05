package com.mvc.service;

import java.util.List;

import org.hibernate.Criteria;

import com.mvc.entity.Commoditiez;
import com.mvc.entity.CommodityCondition;
import com.mvc.util.ResultFilter;

public interface ICommoditiezService {
	public void addCommoditiez(long id,Commoditiez commoditiez);
	public void updateCommoditiez(Commoditiez commodites);
	public void deleteCommoditiez(Commoditiez commodites);
	public List<?> findAll();
	public Commoditiez findCommod(long id);
	
	public int getComoditieCount();
	public List<Commoditiez> getCommoditiezForPage(long id,int startIndex,int numPage);
	public void listCommodites(CommodityCondition condition,ResultFilter<Commoditiez> rf);
	
	List<Commoditiez> getCommodByType(String type);
	
	public Criteria queryCondition(CommodityCondition condition,int startIndex,int numPage);
}
