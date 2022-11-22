package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.FormQuestionDetail;
import com.project.Config.HibernateSessionFactoryConfig;
@Repository
public class FormQuestionDetailRepositoryImpl implements FormQuestionDetailRepository{

	@Autowired
	HibernateSessionFactoryConfig sessionFactory;

	@Autowired
	EntityManager entityManager;

	
	@Override
	public void save(FormQuestionDetail formquestiondetail) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(formquestiondetail);
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
	public void update(FormQuestionDetail formquestiondetail) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.update(formquestiondetail);
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
	public void delete(FormQuestionDetail formquestiondetail) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete((session.contains(formquestiondetail)) ? formquestiondetail : session.merge(formquestiondetail));
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
	public FormQuestionDetail findById(Long quedetailid) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			FormQuestionDetail formquestiondetail = session.get(FormQuestionDetail.class, quedetailid);
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
	public List<FormQuestionDetail> findAllQue() {
		Session session = entityManager.unwrap(Session.class);
		String countQuery = "select * from form_question_detail";

		System.out.println("------------------------find All Questions--------------");
		return session.createNativeQuery(countQuery, FormQuestionDetail.class).getResultList();

	}

	@Override
	public List<FormQuestionDetail> findByFormid(Long formid) {
		Session session = entityManager.unwrap(Session.class);
		String QueListBYFormid = "select * from form_question_detail where formid="+formid;
		System.out.println("------------------------find All Questions BY FORM ID--------------");
		return session.createNativeQuery(QueListBYFormid, FormQuestionDetail.class).getResultList();

	}


}
