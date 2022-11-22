package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.Register;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	EntityManager entityManager;
	@Autowired
	Register register;

	@Override
	public void save(Register register) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = entityManager.unwrap(Session.class);
			transaction = session.beginTransaction();
			session.save(register);
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
	public Register ValidateUser(String email, String password) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = entityManager.unwrap(Session.class);
			
			transaction = session.beginTransaction();
			this.register = (Register) session.createQuery("FROM Register WHERE email= :email AND password= :password").setParameter("email", email).setParameter("password", password).uniqueResult();
			transaction.commit();
			return register;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return register;

	}

	@Override
	public void update(Register register) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Register register) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Register> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register findById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
