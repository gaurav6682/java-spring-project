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
import com.project.Util.CategoryUtil;
import com.project.datatable.DataTableConfig;
import com.project.model.ProductMaster;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	@Autowired
	HibernateSessionFactoryConfig sessionFactory;

	@Autowired
	EntityManager entityManager;

	@Override
	public void save(ProductMaster productmaster) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(productmaster);
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
	public void update(ProductMaster productMaster) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.update(productMaster);
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
	public void delete(ProductMaster productMaster) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete((session.contains(productMaster)) ? productMaster : session.merge(productMaster));
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
	public ProductMaster findById(Integer productId) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ProductMaster productModel = session.get(ProductMaster.class, productId);
			transaction.commit();
			return productModel;
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
	public Map<String, Object> getPaginatedData(HttpServletRequest request) {
		Map<String, Object> pageMap = new HashMap<>();
		DataTableConfig dataTableConfig = new DataTableConfig(request);

		 //String mainQuery = "SELECT PRODUCTID,PRODUCTNAME,PRODUCTDESCRIPTION,CATEGORYID,PRODUCTPRICE,PRODUCTDISCOUNT,PRODUCTIMAGE,ACTIVE  FROM productmaster";

		String mainQuery = "SELECT productmaster.PRODUCTID,productmaster.PRODUCTNAME,productmaster.PRODUCTDESCRIPTION,categorymaster.CATEGORYNAME,productmaster.PRODUCTPRICE,productmaster.PRODUCTDISCOUNT,productmaster.PRODUCTIMAGE,productmaster.ACTIVE FROM springdb.productmaster join categorymaster on productmaster.categoryid = categorymaster.categoryid";
		String whereClause = "WHERE productmaster.PRODUCTID = ?1 OR productmaster.PRODUCTNAME LIKE CONCAT('%',?1,'%')";

		String countQuery = "SELECT COUNT(*) FROM productmaster";

		String orderBy = "ORDER BY productid asc";

		// SessionFactory sessionFactory = (SessionFactory)
		// entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);

		Session session = entityManager.unwrap(Session.class);

		Long recordTotal = ((BigInteger) session.createNativeQuery(countQuery).getSingleResult()).longValue();
		Long recordsFiltered;
		List<?> productList = null;

		if (dataTableConfig.getSearch().trim().length() > 0 && dataTableConfig.getSearch() != null
				&& !"".equals(dataTableConfig.getSearch())) {

			mainQuery += " " + whereClause + " " + orderBy;
			countQuery += " " + whereClause;

			productList = session.createNativeQuery(mainQuery).setParameter(1, dataTableConfig.getSearch())
					.setFirstResult(dataTableConfig.getStart().intValue())
					.setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger) session.createNativeQuery(countQuery)
					.setParameter(1, dataTableConfig.getSearch()).getSingleResult()).longValue();
		} else {
			productList = session.createNativeQuery(mainQuery + " " + orderBy)
					.setFirstResult(dataTableConfig.getStart().intValue())
					.setMaxResults(dataTableConfig.getLength().intValue()).getResultList();
			recordsFiltered = ((BigInteger) session.createNativeQuery(countQuery).getSingleResult()).longValue();
		}
		pageMap.put("recordsTotal", recordTotal);
		pageMap.put("recordsFiltered", recordsFiltered);
		pageMap.put("userList", productList);
		System.out.println(productList.toString());
		return pageMap;
	}

	@Override
	public List<ProductMaster> findAllProducts() {
		Session session = entityManager.unwrap(Session.class);
		String countQuery = "select * from productmaster";

		System.out.println("------------------------find All Product--------------");
		return session.createNativeQuery(countQuery, ProductMaster.class).getResultList();

	}

}
