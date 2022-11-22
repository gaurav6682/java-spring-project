package com.project.repository;

import java.util.List;

import com.project.model.Register;


public interface UserRepository {

	public void save(Register register);

	public void update(Register register);

	public void delete(Register register);

	public List<Register> findAll();
	
	public Register findById(Integer userId);

	public Register ValidateUser(String email, String password);

}

