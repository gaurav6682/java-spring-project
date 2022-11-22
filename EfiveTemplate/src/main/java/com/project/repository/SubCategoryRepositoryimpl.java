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
import com.project.model.SubCategoryMaster;

@Repository
public class SubCategoryRepositoryimpl implements SubCategoryRepository {

	@Autowired
	HibernateSessionFactoryConfig sessionFactory; 
	
	@Autowired
	EntityManager entityManager;
	
	
	
	@Override
	public void save(SubCategoryMaster subcategorymaster) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(subcategorymaster);
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
	public void update(SubCategoryMaster subcategoryMaster) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.update(subcategoryMaster);
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
	public void delete(SubCategoryMaster subcategoryMaster) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete((session.contains(subcategoryMaster)) ? subcategoryMaster : session.merge(subcategoryMaster));
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
	public SubCategoryMaster findById(Long subcategoryid) {

		Session session  = null;
		Transaction transaction = null;
		try 
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			SubCategoryMaster subcatModel = session.get(SubCategoryMaster.class, subcategoryid);
			transaction.commit();
			return subcatModel;
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
		
		String mainQuery = "SELECT 	SUBCATEGORYID,CATEGORYNAME,SUBCATEGORYNAME,SUBCATEGORYDESCRIPTION,ACTIVE FROM subcategorymaster";
		
		String whereClause = "WHERE SUBCATEGORYID = ?1 OR SUBCATEGORYNAME LIKE CONCAT('%',?1,'%')";
		
		String countQuery = "SELECT COUNT(*) FROM subcategorymaster";
		
		String orderBy = "ORDER BY subcategoryid asc"; 
		//+ dataTableConfig.getColumnName() + " " + dataTableConfig.getSortDir();
		
		//SessionFactory sessionFactory = (SessionFactory) entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);

		
		Session session = entityManager.unwrap(Session.class);
		
		Long recordTotal = ((BigInteger)session.createNativeQuery(countQuery).getSingleResult()).longValue();
		Long recordsFiltered;
		List<?> subcatList = null;
		
		if(dataTableConfig.getSearch().trim().length() > 0 && dataTableConfig.getSearch() != null && !"".equals(dataTableConfig.getSearch()))
		{
			mainQuery += " "+ whereClause + " "+ orderBy;
			countQuery += " "+whereClause;
			
			subcatList = session.createNativeQuery(mainQuery).setParameter(1,dataTableConfig.getSearch()).setFirstResult(dataTableConfig.getStart().intValue()).setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger)session.createNativeQuery(countQuery).setParameter(1,dataTableConfig.getSearch()).getSingleResult()).longValue();
		}
		else {
			subcatList = session.createNativeQuery(mainQuery + " " + orderBy).setFirstResult(dataTableConfig.getStart().intValue()).setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger)session.createNativeQuery(countQuery).getSingleResult()).longValue();
		}
		
		pageMap.put("recordsTotal",recordTotal);
		pageMap.put("recordsFiltered",recordsFiltered);
		pageMap.put("subcatList", subcatList);
		
		
		
		return pageMap;
	}

	@Override
	public List<SubCategoryMaster> findAllSubCategorys() {
		Session session = entityManager.unwrap(Session.class);
		String countQuery = "select * from subcategorymaster";
	    return session.createNativeQuery(countQuery, SubCategoryMaster.class).getResultList();
	    
	}

	
}
