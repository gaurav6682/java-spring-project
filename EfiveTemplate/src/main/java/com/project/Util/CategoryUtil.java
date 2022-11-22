	package com.project.Util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.Config.HibernateSessionFactoryConfig;

@Repository
@Transactional
public class CategoryUtil {
	@Autowired
	HibernateSessionFactoryConfig hsessionFactory;
	

	
	public List<?> getCategory(){
		
		Transaction transaction = null;
		List<?> categoryList = null;
		
		Session session= hsessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		categoryList = session.createNativeQuery("select * from categorymaster").getResultList();
		System.out.println("------------->"+categoryList);
		transaction.commit();
		return categoryList;
		
		
	}
	
	public List<?> getSubcategory(Integer subcategoryid)
	{		
		List<?> subcategoryList = null;
		Transaction transaction = null;
		
		Session session= hsessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		//System.out.println("------------sub category---------------");
		subcategoryList = session.createNativeQuery("Select subcategoryid,subcategoryname from subcategorymaster where categoryid= :categoryid").setParameter("categoryid", subcategoryid).getResultList();
//		System.out.println("-------------->>>>>"+subcategoryList);
		transaction.commit();
		return subcategoryList;
	}

}
