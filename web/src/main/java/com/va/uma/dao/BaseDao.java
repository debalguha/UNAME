package com.va.uma.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
//
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void save(Object obj) {
		getSession().save(obj);
	}

	protected void update(Object obj) {
		getSession().update(obj);
	}

	protected void delete(Object obj) {
		getSession().delete(obj);
	}

	@SuppressWarnings("rawtypes")
	protected Object findById(Class clazz, String id) {
		return getSession().get(clazz, id);
	}
}
