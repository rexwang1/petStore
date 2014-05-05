package com.mvc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public interface ManagerDao {

	public void add(Object o);
	public void delete(Object o);
	public void update(Object o);
	public List<?> findAll(String entityName);
	public int getCount(String entityName);
	public Object findOne(Class<?> clasz,Serializable id);
	
	public Query getQuery(String hql);
	/**
	 * 
	 * @param entityName 实体类名
	 * @param currentPage 开始记录数
	 * @param numPage  页记录数
	 * @return
	 */
	public List<?> getListForPage(String entityName,int startIndex,int numPage);
	
	public Query getNameQuery(String nameQuery);
	
	public SessionFactory getSessionFactory();
	
	public void setSessionFactory(SessionFactory sessionFactory);
	Criteria queryCondition(Class<?> cla, int startIndex, int numPage);
	
	
}
