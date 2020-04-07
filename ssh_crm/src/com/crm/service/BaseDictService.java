package com.crm.service;

import java.util.List;

import com.crm.entity.BaseDict;
/**
 * 数据字典的业务层
 * @author 骄傲的大树
 *
 */
public interface BaseDictService {

	public List<BaseDict> findByTypeCode(String dict_type_code);
}
