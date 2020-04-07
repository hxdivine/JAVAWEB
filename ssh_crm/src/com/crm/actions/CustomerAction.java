package com.crm.actions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.entity.Customer;
import com.crm.entity.PageBean;
import com.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.UploadUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	//封装数据
	private Customer customer = new Customer();
	
	
	//调用模型驱动
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//用set封装currPage pageSize
	private Integer currPage = 1;
	private Integer pageSize = 3;
	

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public Integer getPageSize() {
	
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
	}
	
	//文件上传封装
	private String uploadFileName; //文件路径
	private File upload;  //上传文件
	private String uploadContentType; //文件类型
	
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	//查找全部客户
	public String find(){

		// 接收参数：分页参数
		// 最好使用DetachedCriteria对象（条件查询--带分页）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//是否有条件查询
		if(customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		if(customer.getBaseDictSource() != null) {
			if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if(customer.getBaseDictIndustry() != null) {
			if(customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		if(customer.getBaseDictLevel() != null) {
			if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		// 调用业务层查询:
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
				

		return "findSuccess";
	}
	
	//保存客户
	public String save() throws IOException {
		//上传图片
		if(upload != null) {
			//文件上传
			//设置文件上传的路径
			String path = "E:/upload";
			//一个目录存在相同的文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//一个目录存在过多的文件：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdir();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}
	
	//转跳到新增页面
	public String addUi() {
		return "addUi";
	}

	public String edit() throws IOException {
		if(upload != null) {
			//已选择删除原有文件
			String cust_image = customer.getCust_image();
			File file1 = new File(cust_image);
			file1.delete();
			
			String path = "E:/upload";
			//一个目录存在相同的文件名：随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//一个目录存在过多的文件：目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdir();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		return "editSuccess";
	}
	
	//转跳到新增页面
	public String editUi() {
	
		Customer customer1 = customerService.findByid(customer.getCust_id());
		//request.setAttribute("customer", customer);
		ServletActionContext.getRequest().setAttribute("customer", customer1);
		return "editUi";
	}
	
	public String delete() {
		
		Customer customer1 = customerService.findByid(customer.getCust_id());
		if(customer1.getCust_image() != null) {
			//删除上传的文件
			File file = new File(customer1.getCust_image());
			file.delete();
		}
		
		customerService.delete(customer1);
		
		return "delSuccess";
	}
	
	/**
	 * 根据类型查询客户信息
	 * @return
	 * @throws IOException
	 */
	public String CustomerList() throws IOException{
		List<Customer> list = customerService.findAll();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"cust_image","cust_phone","cust_mobile","baseDictSource",
				"baseDictLevel","baseDictIndustry","linkMans"});
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
