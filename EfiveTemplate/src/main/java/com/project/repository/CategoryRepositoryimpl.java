package com.project.repository;



import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Config.HibernateSessionFactoryConfig;
import com.project.datatable.DataTableConfig;
import com.project.model.CategoryMaster;

@Repository
public class CategoryRepositoryimpl implements CategoryRepository {
	@Autowired
	HibernateSessionFactoryConfig sessionFactory; 
	
	@Autowired
	EntityManager entityManager;
	
	
	
	
	@Override
	public void save(CategoryMaster categorymaster) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(categorymaster);
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
	public void update(CategoryMaster categoryMaster) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.update(categoryMaster);
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
	public void delete(CategoryMaster categoryMaster) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete((session.contains(categoryMaster)) ? categoryMaster : session.merge(categoryMaster));
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
	public CategoryMaster findById(Long categoryid) {

		Session session  = null;
		Transaction transaction = null;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CategoryMaster productModel = session.get(CategoryMaster.class, categoryid);
			transaction.commit();
			return productModel;
		}  catch (HibernateException e) {
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
	public Map<String,Object> getPaginatedData(HttpServletRequest request)
	{
		Map<String,Object> pageMap = new HashMap<>();
		DataTableConfig dataTableConfig = new DataTableConfig(request);
		
		String mainQuery = "SELECT CATEGORYID,CATEGORYNAME,CATEGORYDESCRIPTION,ACTIVE FROM categorymaster";
		
		String whereClause = "WHERE CATEGORYID = ?1 OR CATEGORYNAME LIKE CONCAT('%',?1,'%')";
		
		String countQuery = "SELECT COUNT(*) FROM categorymaster";
		
		String orderBy = "ORDER BY categoryid asc"; 

		
		Session session = entityManager.unwrap(Session.class);
		
		Long recordTotal = ((BigInteger)session.createNativeQuery(countQuery).getSingleResult()).longValue();
		Long recordsFiltered;
		List<?> categoryList = null;
		
		if (dataTableConfig.getSearch().trim().length() > 0 && dataTableConfig.getSearch() != null
				&& !"".equals(dataTableConfig.getSearch()))
		{
			mainQuery += " "+ whereClause + " "+ orderBy;
			countQuery += " "+whereClause;
			
			categoryList = session.createNativeQuery(mainQuery).setParameter(1,dataTableConfig.getSearch()).setFirstResult(dataTableConfig.getStart().intValue()).setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger)session.createNativeQuery(countQuery).setParameter(1,dataTableConfig.getSearch()).getSingleResult()).longValue();
		}
		else {
			categoryList = session.createNativeQuery(mainQuery + " " + orderBy).setFirstResult(dataTableConfig.getStart().intValue()).setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger)session.createNativeQuery(countQuery).getSingleResult()).longValue();
		}
		
		pageMap.put("recordsTotal",recordTotal);
		pageMap.put("recordsFiltered",recordsFiltered);
		pageMap.put("categoryList", categoryList);
		
		
		
		return pageMap;
	}

	@Override
	public List<CategoryMaster> findAllCategorys() {
		Session session = entityManager.unwrap(Session.class);
		String countQuery = "select * from categorymaster";
		System.out.println("------------------------find All Category--------------");
		System.out.println(session.createNativeQuery(countQuery, CategoryMaster.class).getResultList());
	    return session.createNativeQuery(countQuery, CategoryMaster.class).getResultList();
	    
	}
	
	
}





