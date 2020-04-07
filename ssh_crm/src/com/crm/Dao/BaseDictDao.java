package com.crm.Dao;

import java.util.List;

import com.crm.entity.BaseDict;

/**
 * 数据字典的持久层
 * @author 骄傲的大树
 *
 */
public interface BaseDictDao {
	public List<BaseDict> findByTypeCode(String dict_type_code);
}
