package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.FormQuestionDetail;
import com.project.model.FromQuestionOption;
import com.project.Config.HibernateSessionFactoryConfig;
@Repository
public class FormQuestionOptionRepositoryImpl implements FormQuestionOptionRepository{

	@Autowired
	HibernateSessionFactoryConfig sessionFactory;

	@Autowired
	EntityManager entityManager;

	
	@Override
	public void save(FromQuestionOption formquestionoption) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(formquestionoption);
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
	public void update(FromQuestionOption formquestionoption) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.update(formquestionoption);
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
	public void delete(FromQuestionOption formquestionoption) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete((session.contains(formquestionoption)) ? formquestionoption : session.merge(formquestionoption));
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
	public FromQuestionOption findById(Long optionid) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			FromQuestionOption formquestiondetail = session.get(FromQuestionOption.class, optionid);
			transaction.commit();
			return formquestiondetail;
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
	public List<FromQuestionOption> findAllOption() {
		Session session = entityManager.unwrap(Session.class);
		String countQuery = "select * from form_question_option";

		System.out.println("------------------------find All Questions--------------");
		return session.createNativeQuery(countQuery, FromQuestionOption.class).getResultList();

	}
	
	@Override
	public List<FromQuestionOption> findByFormid(Long formid) {
		Session session = entityManager.unwrap(Session.class);
		String QueListBYFormid = "select * from form_question_option where formid="+formid;
		System.out.println("------------------------find All OPtion BY FORM ID--------------");
		return session.createNativeQuery(QueListBYFormid, FromQuestionOption.class).getResultList();
	}

	@Override
	public List<FromQuestionOption> findByQuedetailid(Long quedetailid) {
		Session session = entityManager.unwrap(Session.class);
		String OptionListBYQuedetailid = "select * from form_question_option where quedetailid="+ quedetailid;
		System.out.println("------------------------find All OPtion BY QUE ID--------------");
		return session.createNativeQuery(OptionListBYQuedetailid, FromQuestionOption.class).getResultList();
	}

}
