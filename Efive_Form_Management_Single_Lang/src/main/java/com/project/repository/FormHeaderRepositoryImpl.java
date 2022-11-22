package com.project.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Config.HibernateSessionFactoryConfig;
import com.project.model.FormHeader;

@Repository
public class FormHeaderRepositoryImpl implements FormHeaderRepository {
	@Autowired
	HibernateSessionFactoryConfig sessionFactory;

	@Autowired
	EntityManager entityManager;

	@Override
	public void save(FormHeader formheader) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(formheader);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void update(FormHeader formheader) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.update(formheader);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				System.err.println("transaction failed, rolling back");
				transaction.rollback();
			} else {
				session.clear();
				session.close();
			}
		}
	}

	@Override
	public void delete(FormHeader formheader) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete((session.contains(formheader)) ? formheader : session.merge(formheader));
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				System.err.println("transaction failed, rolling back");
				transaction.rollback();
			} else {
				session.clear();
				session.close();
			}
		}
	}

	@Override
	public FormHeader findById(Long formId) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			FormHeader formheader = session.get(FormHeader.class, formId);
			transaction.commit();
			return formheader;
		} catch (HibernateException e) {
			if (transaction != null) {
				System.err.println("transaction failed, rolling back");
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	

	@Override
	public List<FormHeader> findAllForm() {
		Session session = entityManager.unwrap(Session.class);
		String countQuery = "select * from form_header";

		System.out.println("------------------------find All FormData--------------");
		return session.createNativeQuery(countQuery, FormHeader.class).getResultList();

	}

}
