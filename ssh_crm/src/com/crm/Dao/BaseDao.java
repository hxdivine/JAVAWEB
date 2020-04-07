package com.crm.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
/**
 * 通用Dao持久层
 * @author 骄傲的大树
 *
 * @param <T>  泛型
 */

public interface BaseDao<T> {
	public void save(T t);
	
	public T findByid(Serializable id);

	public void update(T t);

	public void delete(T t);

	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	public Integer findCount(DetachedCriteria detachedCriteria);

	public List<T> findAll();
}
