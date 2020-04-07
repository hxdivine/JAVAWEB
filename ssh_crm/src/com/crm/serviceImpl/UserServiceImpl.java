package com.crm.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.crm.Dao.UserDao;
import com.crm.entity.User;
import com.crm.service.UserService;
import com.util.MD5Utils;
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}
	public boolean register(User user) {
		//密码加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		return userDao.register(user);
	}
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
