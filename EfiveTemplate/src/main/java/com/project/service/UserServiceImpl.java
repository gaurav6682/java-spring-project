package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Register;
import com.project.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(Register register) {
		userRepository.save(register);

	}

	@Override
	public void update(Register register) {

		
	}

	@Override
	public void delete(Register register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Register> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register ValidateUser(String email, String password) {
		Register register =  userRepository.ValidateUser(email, password);
		return register;
	}

}
