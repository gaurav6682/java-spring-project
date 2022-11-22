

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
	import com.project.model.FillForm;
import com.project.model.FormQuestionDetail;

	@Repository
	public class FillFormRepositoryimpl implements FillFormRepository{
	
	
		@Autowired
		HibernateSessionFactoryConfig sessionFactory;

		@Autowired
		EntityManager entityManager;

		@Override
		public void save(FillForm fillform) {
			Session session = null;
			Transaction transaction = null;
			try {
				session = this.sessionFactory.getCurrentSession();
				transaction = session.beginTransaction();
				session.saveOrUpdate(fillform);
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
		public void update(FillForm fillform) {

			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.getCurrentSession();
				transaction = session.beginTransaction();
				session.update(fillform);
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
		public void delete(FillForm fillform) {
			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.getCurrentSession();
				transaction = session.beginTransaction();
				session.delete((session.contains(fillform)) ? fillform : session.merge(fillform));
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
		public FillForm findById(Long ansid) {

			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				FillForm fillform = session.get(FillForm.class, ansid);
				transaction.commit();
				return fillform;
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
		public List<FillForm> findAllForm() {
			Session session = entityManager.unwrap(Session.class);
			String countQuery = "select * from fill_form_details";

			System.out.println("------------------------find All FormData--------------");
			return session.createNativeQuery(countQuery, FillForm.class).getResultList();

		}
		
		@Override
		public List<FillForm> findByFormid(Long formid) {
			Session session = entityManager.unwrap(Session.class);
			String QueListBYFormid = "select * from fill_form_details where formid="+formid;
			System.out.println("------------------------find All Questions BY FORM ID--------------");
			return session.createNativeQuery(QueListBYFormid, FillForm.class).getResultList();

		}

	}
