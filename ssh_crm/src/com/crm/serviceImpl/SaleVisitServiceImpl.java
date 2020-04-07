package com.crm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.Dao.SaleVisitDao;
import com.crm.entity.PageBean;
import com.crm.entity.SaleVisit;
import com.crm.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService{
	
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc / pageSize);
		
		pageBean.setTotalPage(num.intValue());
		
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	public SaleVisit findByid(long visit_id) {
		return saleVisitDao.findByid(visit_id);
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
	
	
}
