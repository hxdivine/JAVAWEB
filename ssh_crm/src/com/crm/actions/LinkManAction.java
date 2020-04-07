package com.crm.actions;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.entity.LinkMan;
import com.crm.entity.PageBean;
import com.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	private LinkMan linkMan = new LinkMan();;
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
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
			pageSize = 1;
		}
		this.pageSize = pageSize;
	}
	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//是否有条件查询
		if(linkMan.getLkm_name() != null) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender()) ) {
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
			
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String delete() {
		LinkMan linkMan1 = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan1);
		return "delSuccess";
	}
	
	public String add() {
		linkManService.save(linkMan);
		return "addSuccess";
	}
	public String addUi() {
		return "addUi";
	}
	
	public String editUi(){
		LinkMan linkMan1 = linkManService.findById(linkMan.getLkm_id());
		ActionContext.getContext().getValueStack().push(linkMan1);
		return "editUi";
	}
	public String edit(){

		linkManService.update(linkMan);
		return "editSuccess";
	}
}
