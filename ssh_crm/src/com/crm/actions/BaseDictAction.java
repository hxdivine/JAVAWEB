package com.crm.actions;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.crm.entity.BaseDict;
import com.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


public class BaseDictAction extends ActionSupport{


	//注入service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	/**
	 * 根据类型查询数据字典信息
	 * @return
	 * @throws IOException
	 */
	public String findByTypeCode() throws IOException{
		String parameter = ServletActionContext.getRequest().getParameter("dict_type_code");
		List<BaseDict> list = baseDictService.findByTypeCode(parameter);
		
		System.out.println("-------------------"+list.size());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"dict_sort","dict_enable","dict_memo"});
		/**
		 * JSONArray:将数据和list集合转成JSON
		 * JSONOBject：将对象和Map集合转成JSON
		 */
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(list.toString());
		//将JSON打印到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
