package com.crm.interceptor;

import org.apache.struts2.ServletActionContext;

import com.crm.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeDofiterInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 判断session是否存在用户信息
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if(user == null) {
			//没有登录给出提示信息
			ActionSupport action = (ActionSupport)invocation.getAction();
			//提示错误信息
			action.addActionError("没有登录用户");
			//回到登录页面
			return action.LOGIN;
		}else {
			return invocation.invoke();
		}
	}

}
