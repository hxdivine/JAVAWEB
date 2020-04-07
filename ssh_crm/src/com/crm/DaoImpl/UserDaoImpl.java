package com.crm.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.Dao.UserDao;
import com.crm.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User login(User user) {
		List<User> list = new ArrayList<User>();
		String[] paramName = new String[] {"code","pwd"};
		String[] values = new String[]{user.getUser_code(),user.getUser_password()};
		String hql = "from User where user_code = :code and user_password = :pwd";
		list= (List<User>)this.getHibernateTemplate().findByNamedParam(hql, paramName, values);
		
		if(list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public boolean register(User user) {
		List<User> list= (List<User>)this.getHibernateTemplate().find("from User where user_code = ?",user.getUser_code());
		if(list.size() > 0) {
			return false;
		}else {
			this.getHibernateTemplate().save(user);
			return true;
		}
	}

}
