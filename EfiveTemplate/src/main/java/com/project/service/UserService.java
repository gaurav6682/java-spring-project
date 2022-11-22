package com.project.service;

import java.util.List;

import com.project.model.Register;

public interface UserService {

	public void save(Register register);

	public void update(Register register);

	public void delete(Register register);

	public List<Register> findAll();

	public Register ValidateUser(String email, String password);


}
