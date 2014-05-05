package com.mvc.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.ManagerDao;
import com.mvc.entity.Commoditiez;
import com.mvc.entity.CommodityCondition;
import com.mvc.entity.Inventory;
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
	public void addCommoditiez(long id,Commoditiez commoditiez) {
		// TODO Auto-generated method stub
		Inventory inventory = (Inventory) managerDao.findOne(Inventory.class, id);
		commoditiez.setInventory(inventory);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Commoditiez> getCommoditiezForPage(long id, int startIndex,
			int numPage) {
		// TODO Auto-generated method stub
		
		//List<Commoditiez> result = managerDao.queryCondition(Commoditiez.class, startIndex, numPage);
		return (List<Commoditiez>) managerDao.getListForPage(Commoditiez.class.getSimpleName(), startIndex, numPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void listCommodites(CommodityCondition condition, ResultFilter<Commoditiez> rf) {
		Criteria criteria = queryCondition(condition, (rf.getCurrentPage()-1) * rf.getPageSize(),
				rf.getPageSize());
		rf.setItems((List<Commoditiez>)criteria.list());
		criteria.setProjection(Projections.rowCount());
		rf.setTotalCount((Integer)criteria.uniqueResult());
	}

	@Override
	public Criteria queryCondition(CommodityCondition condition,int startIndex,int numPage) {
		// TODO Auto-generated method stub
		if(condition == null){
			return null;
		}
		Criteria comCriteria = managerDao.queryCondition(Commoditiez.class, startIndex, numPage);
		comCriteria = comCriteria.createAlias("inventory", "inventory");
		
		
		
		if(validStr(condition.getBrand())){
			comCriteria.add(Restrictions.like("inventory.brand", "%"+condition.getBrand()+"%"));
		}
		
		if(validStr(condition.getName())){
			comCriteria.add(Restrictions.like("inventory.name", "%"+condition.getName()+"%"));
		}
		
		if(!condition.getType().trim().equals("-1")){
			comCriteria.add(Restrictions.eq("inventory.type", condition.getType()));
			System.err.println("type:"+condition.getType());
		}
		System.out.println("Condition: "+condition.isCanOrder());
		if(condition.isCanOrder()){
			String orderByPrice = condition.getPriceOrder();
			if(orderByPrice == "desc"){
				comCriteria.addOrder(Order.desc("price"));
			}else{
				comCriteria.addOrder(Order.asc("price"));
			}
		}
		
		
		if((condition.getLowPrice()) >0){
			comCriteria.add(Restrictions.ge("price", condition.getLowPrice()));
		}
		
		if((condition.getHighPrice()) >0){
			comCriteria.add(Restrictions.lt("price", condition.getHighPrice()));
		}
		
		return comCriteria;
	}
	
	private boolean validStr(String value){
		return null != value && !value.trim().equals("");
	}
}
