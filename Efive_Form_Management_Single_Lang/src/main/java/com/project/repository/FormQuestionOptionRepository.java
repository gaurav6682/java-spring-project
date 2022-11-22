package com.project.repository;

import java.util.List;

import com.project.model.FromQuestionOption;

public interface FormQuestionOptionRepository {
	public void save(FromQuestionOption formquestionoption);

	public void update(FromQuestionOption formquestionoption);

	public void delete(FromQuestionOption formquestionoption);

	public FromQuestionOption findById(Long optionlid);

	public List<FromQuestionOption> findAllOption();

	List<FromQuestionOption> findByFormid(Long formid);

	List<FromQuestionOption> findByQuedetailid(Long quedetailid);
}
