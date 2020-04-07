package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.entity.LinkMan;
import com.crm.entity.PageBean;

/**
 * 联系人的业务层
 * @author 骄傲的大树
 *
 */
public interface LinkManService {

	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	public void delete(LinkMan linkMan);

	public void save(LinkMan linkMan);

	public LinkMan findById(long lkm_id);

	public void update(LinkMan linkMan);

}
