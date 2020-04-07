package com.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtils
 *
 *
 */
public class HibernateUtils {

	private static SessionFactory sessionFactory;

	
	private static ThreadLocal<Session> session = new ThreadLocal<Session>();


	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException("��ʼ���Ự����ʧ�ܣ�");
		}

	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	//��ȡһ����session
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	

 
	public static Session getCurrentSession() throws HibernateException {
		return sessionFactory.getCurrentSession(); 
	}

	
	public static void closeSession() throws HibernateException {
		sessionFactory.getCurrentSession().close();
	}

}
