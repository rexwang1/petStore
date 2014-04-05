package com.mvc.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.Commoditiez;
import com.mvc.util.ResultFilter;
/**
 * 
 * @author root
 * 主要是管理员的操作
 *
 */
@Service("commoditiesService")
public class CommoditiesServiceImpl implements ICommoditiezService{

	@Autowired
	private ManagerDao managerDao;
	
	@Override
	public void addCommoditiez(Commoditiez commoditiez) {
		// TODO Auto-generated method stub
		managerDao.add(commoditiez);
	}

	@Override
	public void updateCommoditiez(Commoditiez commodites) {
		// TODO Auto-generated method stub
		managerDao.update(commodites);
	}

	@Override
	public void deleteCommoditiez(Commoditiez commodites) {
		// TODO Auto-generated method stub
		managerDao.delete(commodites);
	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return managerDao.findAll(Commoditiez.class.getSimpleName());
	}

	@Override
	public Commoditiez findCommod(long id) {
		// TODO Auto-generated method stub
		return (Commoditiez) managerDao.findOne(Commoditiez.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Commoditiez> getCommodByType(String type){
		Query query = managerDao.getNameQuery("findCommodByType");
		query.setString("commodities", type);
		return query.list();
	}

	@Override
	public int getComoditieCount() {
		// TODO Auto-generated method stub
		return managerDao.getCount(Commoditiez.class.getSimpleName());
	}

	@Override
	public List<Commoditiez> getCommoditiezForPage(long id, int startIndex,
			int numPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listCommodites(long id, ResultFilter<Commoditiez> rf) {
		// TODO Auto-generated method stub
		
	}
}
