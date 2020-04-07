package com.crm.serviceImpl;

import java.util.List;

import com.crm.Dao.BaseDictDao;
import com.crm.entity.BaseDict;
import com.crm.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService{

	private BaseDictDao baseDictDao;
	
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}

	
}
