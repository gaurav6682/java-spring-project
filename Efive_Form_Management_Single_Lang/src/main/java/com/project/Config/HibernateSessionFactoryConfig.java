package com.project.Config;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateSessionFactoryConfig {
	@Autowired
	EntityManager entityManager;
	
	
	private final SessionFactory sessionFactory() {
		SessionFactory sessionFactory = (SessionFactory) entityManager.unwrap(Session.class).getSessionFactory();
		if(sessionFactory == null) {
			throw new NullPointerException("Unable to inject hibernate session factory.");
		} else {
			return sessionFactory;
		}
	}
	
	public Session getCurrentSession() {
		return sessionFactory().getCurrentSession();
	}
	
	public Session openSession() {
		return sessionFactory().openSession();
	}
}
