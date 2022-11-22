package com.project.repository;

import java.util.List;

import com.project.model.FormQuestionDetail;

public interface FormQuestionDetailRepository {
	
	public void save(FormQuestionDetail formquestiondetail);

	public void update(FormQuestionDetail formquestiondetail);

	public void delete(FormQuestionDetail formquestiondetail);

	public FormQuestionDetail findById(Long quedetailid);

	public List<FormQuestionDetail> findAllQue();

	public List<FormQuestionDetail> findByFormid(Long formid);

}
