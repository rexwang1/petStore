package com.mvc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("managerDao")
public class ManagerDaoImpl implements ManagerDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(o);
		
	}

	@Transactional
	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(o);
	}

	@Transactional
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(o);
	}

	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly=true)
	@Override
	public List<?> findAll(String entityName) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from "+entityName).list();
	}

	@Transactional(readOnly=true)
	@Override
	public List<?> getListForPage(String entityName, int startIndex,
			int numPage) {
		Query query = sessionFactory.getCurrentSession().createQuery("from "+entityName);
		query.setFirstResult(startIndex);
		query.setMaxResults(numPage);
		
		return query.list();
	}

	@Transactional(readOnly=true)
	@Override
	public int getCount(String entityName) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + entityName;
		
		return (Integer) sessionFactory.getCurrentSession().createQuery(hql).list().get(0);
	}

	@Transactional(readOnly=true)
	@Override
	public Object findOne(Class<?> clasz,Serializable id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(clasz,id);
	}

	@Override
	public Query getQuery(String hql) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery(hql);
		
		
	}

	@Transactional(readOnly=true)
	@Override
	public Query getNameQuery(String nameQuery) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().getNamedQuery(nameQuery);
	}
	
	
	
}
