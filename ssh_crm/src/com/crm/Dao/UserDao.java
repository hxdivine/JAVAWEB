package com.crm.Dao;

import com.crm.entity.User;

public interface UserDao extends BaseDao<User>{

	public User login(User user);
	
	public boolean register(User user);
}
