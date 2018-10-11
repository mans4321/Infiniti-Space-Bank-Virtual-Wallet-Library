package com.infiniti_space_bank.virtual_wallet.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}
}
