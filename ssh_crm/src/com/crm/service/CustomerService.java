package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.entity.Customer;
import com.crm.entity.PageBean;

public interface CustomerService {
	

	public void save(Customer customer);
	
	public Customer findByid(long cust_id);

	public void update(Customer customer);

	public void delete(Customer customer);

	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	public List<Customer> findAll();
}
