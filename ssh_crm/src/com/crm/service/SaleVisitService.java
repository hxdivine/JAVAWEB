package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.entity.PageBean;
import com.crm.entity.SaleVisit;

/**
 * 拜访记录的业务层
 * @author 骄傲的大树
 *
 */
public interface SaleVisitService {

	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	public void delete(SaleVisit saleVisit);

	public SaleVisit findByid(long visit_id);

	public void save(SaleVisit saleVisit);

	public void update(SaleVisit saleVisit);

}
