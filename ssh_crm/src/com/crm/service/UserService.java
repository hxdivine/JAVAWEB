package com.crm.service;

import java.util.List;

import com.crm.entity.User;

public interface UserService {

	public User login(User user);
	
	public boolean register(User user);

	public List<User> findAll();
}
