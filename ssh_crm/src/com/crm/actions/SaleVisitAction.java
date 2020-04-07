package com.crm.actions;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.entity.PageBean;
import com.crm.entity.SaleVisit;
import com.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		// TODO Auto-generated method stub
		return saleVisit;
	}
	
	//属性注入SaleVisitService
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;

	private Integer currPage = 1;
	private Integer pageSize = 3;
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	private Date visit_end_time;
	
	public Date getVisit_end_time() {
		return visit_end_time;
	}

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	/**
	 * 用户拜访记录查询所有
	 * @return
	 */
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getVisit_time() != null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String delete() {
		SaleVisit saleVisit1 = saleVisitService.findByid(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit1);
		return "delSuccess";
	}

	public String addUi() {
		return "addUi";
	}
	
	public String add() {
		saleVisitService.save(saleVisit);
		return "addSuccess";
	}
	
	public String editUi() {
		SaleVisit saleVisit1 = saleVisitService.findByid(saleVisit.getVisit_id());
		ActionContext.getContext().getValueStack().push(saleVisit1);
		return "editUi";
	}
	
	public String edit() {
		saleVisitService.update(saleVisit);
		return "editSuccess";
	}
}
