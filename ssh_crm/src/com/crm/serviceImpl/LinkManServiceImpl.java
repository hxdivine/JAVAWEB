package com.crm.serviceImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.Dao.LinkManDao;
import com.crm.entity.LinkMan;
import com.crm.entity.PageBean;
import com.crm.service.LinkManService;
@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		Integer begin = (currPage - 1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
		
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
		
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public LinkMan findById(long lkm_id) {
		return linkManDao.findByid(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
	
}
