package com.project.repository;
import java.util.List;


import com.project.model.*;

public interface FillFormRepository {

		public void save(FillForm formheader);
		
		public void update(FillForm formheader);

		public void delete(FillForm formheader);

		public FillForm findById(Long ansid);


		public List<FillForm> findAllForm();

		List<FillForm> findByFormid(Long formid);
	}

