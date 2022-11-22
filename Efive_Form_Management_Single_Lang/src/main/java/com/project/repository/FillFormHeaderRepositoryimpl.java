package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Config.HibernateSessionFactoryConfig;
import com.project.model.FillForm;
import com.project.model.FillFormHeader;

@Repository
public class FillFormHeaderRepositoryimpl implements FillFormHeaderRepository{
		
		

		@Autowired
		HibernateSessionFactoryConfig sessionFactory;

		@Autowired
		EntityManager entityManager;

		@Override
		public void save(FillFormHeader fillformheader) {
			Session session = null;
			Transaction transaction = null;
			try {	
				session = this.sessionFactory.getCurrentSession();
				transaction = session.beginTransaction();
				session.saveOrUpdate(fillformheader);
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
		public void update(FillFormHeader fillformheader) {

			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.getCurrentSession();
				transaction = session.beginTransaction();
				session.update(fillformheader);
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
		public void delete(FillFormHeader fillformheader) {
			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.getCurrentSession();
				transaction = session.beginTransaction();
				session.delete((session.contains(fillformheader)) ? fillformheader : session.merge(fillformheader));
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
		public FillFormHeader findByFormid(Long formid) {

			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				FillFormHeader fillformheader = session.get(FillFormHeader.class, formid);
				transaction.commit();
				return fillformheader;
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
		public List<FillFormHeader> findAllForm() {
			Session session = entityManager.unwrap(Session.class);
			String countQuery = "select * from fill_form_header";

			System.out.println("------------------------find All FormData--------------");
			return session.createNativeQuery(countQuery, FillFormHeader.class).getResultList();

		}

		
		
//		@Override
//		public List<FillFormHeader> findByFormid(Long formid) {
//			Session session = entityManager.unwrap(Session.class);
//			String QueListBYFormid = "select * from fill_form_header where formid="+formid;
//			System.out.println("------------------------find All Fill FORM BY FORM ID--------------");
//			return session.createNativeQuery(QueListBYFormid, FillFormHeader.class).getResultList();
//
//		}
}
