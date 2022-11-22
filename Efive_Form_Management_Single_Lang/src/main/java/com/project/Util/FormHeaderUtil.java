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
public class FormHeaderUtil {
	@Autowired
	HibernateSessionFactoryConfig hsessionFactory;
	

	
	public List<?> getFormData(){
		
		Transaction transaction = null;
		List<?> formdatalist = null;
		
		Session session= hsessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		formdatalist = session.createNativeQuery("select * from form_header").getResultList();
		System.out.println("------------->"+formdatalist.toString());
		transaction.commit();
		return formdatalist;
		
		
	}
	
public List<?> getQueData(){
		
		Transaction transaction = null;
		List<?> quedatalist = null;
		
		Session session= hsessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		quedatalist = session.createNativeQuery("select * from form_question_detail").getResultList();
		System.out.println("------------->"+quedatalist.toString());
		transaction.commit();
		return quedatalist;
		
		
	}


//Characteristic MAPPING

public List<?> getCharacteristic(Integer moduleid)
{		
	List<?> characteristic = null;
	Transaction transaction = null;
	
	Session session= hsessionFactory.getCurrentSession();
	transaction = session.beginTransaction();
	System.out.println("------------Find characteristic by moduleid---------------");
	characteristic  = session.createNativeQuery("Select mst_characteristics.characteristicId,mst_characteristics.characteristicname from mst_module_characteristics_mapping INNER JOIN mst_characteristics ON  mst_module_characteristics_mapping.characteristicid=mst_characteristics.characteristicid WHERE moduleid="+ moduleid).getResultList();
	
	System.out.println("-------------->>>>>"+characteristic);
	transaction.commit();
	return characteristic ;
}



//SUB-Characteristic MAPPING

public List<?> getSubCharacteristic(Integer characteristicid)
{		
	List<?> subcharacteristic = null;
	Transaction transaction = null;
	
	Session session= hsessionFactory.getCurrentSession();
	transaction = session.beginTransaction();
	System.out.println("------------Find Sub-CHARACTORISTIC by Charactoristic---------------");
	subcharacteristic  = session.createNativeQuery("Select mst_subcharacteristics.subcharacteristicId,mst_subcharacteristics.subcharacteristicname from mst_subcharacteristics WHERE mst_subcharacteristics.characteristicId="+ characteristicid).getResultList();
	
	System.out.println("-------------->>>>>"+subcharacteristic);
	transaction.commit();
	return subcharacteristic ;
}
	
	

public List<?> getAllFillForms()
{		
	List<?> fillform = null;
	Transaction transaction = null;
	
	Session session= hsessionFactory.getCurrentSession();
	transaction = session.beginTransaction();
	System.out.println("------------fill FORM---------------");
	fillform  = session.createNativeQuery("Select fill_form_header.compliteddate, fill_form_header.formid, fill_form_header.userid, form_header.titletext from fill_form_header INNER JOIN form_header ON fill_form_header.formid=form_header.formid").getResultList();
	
	System.out.println("FILL FORM DATA-------------->>>>>"+fillform.toString());
	transaction.commit();
	return fillform;
}




}
