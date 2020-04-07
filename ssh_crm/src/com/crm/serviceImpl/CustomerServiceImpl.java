package com.crm.serviceImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.Dao.CustomerDao;
import com.crm.entity.Customer;
import com.crm.entity.PageBean;
import com.crm.service.CustomerService;
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}


	@Override
	public Customer findByid(long cust_id) {
		return customerDao.findByid(cust_id);
	}


	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}


	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}


	@Override
	//业务层分页查询客户的方法
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		Double tc = totalCount.doubleValue();
		//ceil向上取整
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		//封装每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		
		pageBean.setList(list);
		return pageBean;
	}


	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
