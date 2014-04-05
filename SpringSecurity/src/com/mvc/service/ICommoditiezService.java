package com.mvc.service;

import java.util.List;

import com.mvc.entity.Commoditiez;
import com.mvc.util.ResultFilter;

public interface ICommoditiezService {
	public void addCommoditiez(Commoditiez commoditiez);
	public void updateCommoditiez(Commoditiez commodites);
	public void deleteCommoditiez(Commoditiez commodites);
	public List<?> findAll();
	public Commoditiez findCommod(long id);
	
	public int getComoditieCount();
	public List<Commoditiez> getCommoditiezForPage(long id,int startIndex,int numPage);
	public void listCommodites(long id,ResultFilter<Commoditiez> rf);
	List<Commoditiez> getCommodByType(String type);
	
	
}
