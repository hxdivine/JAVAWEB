package com.crm.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.crm.entity.Customer;
import com.crm.entity.User;
import com.crm.service.CustomerService;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private User user = new User();
	
	public User getModel() {
		return user;
	}
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String regist() {
		boolean flag = userService.register(user);
		if(flag) {
			return "regist";
		}else {
			this.addActionError("用户名已存在");
			return "error";
		}
	}
	public String login() {
		
		User existUser = userService.login(user);
		
		if(existUser == null) {
			//登录失败
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("user", existUser);
			return SUCCESS;
		}
	}
	
	/**
	 * 根据类型查询用户信息
	 * @return
	 * @throws IOException
	 */
	public String UserList() throws IOException{
		List<User> list = userService.findAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"user_code","user_password","user_state"});
		/**
		 * JSONArray:将数据和list集合转成JSON
		 * JSONOBject：将对象和Map集合转成JSON
		 */
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		//将JSON打印到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
