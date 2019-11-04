package cn.edu.zucc.personplan.model;

import java.sql.Date;

import org.hibernate.Session;

import cn.edu.zucc.personplan.util.HibernateUtil;

public class BeanSchoolPlan {	  
	  private int school_id;
	  private String schoolPlan_ask;
	  private Date sign_startTime;
	  private Date sign_endTime;
	  private String sign_method;
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchoolPlan_ask() {
		return schoolPlan_ask;
	}
	public void setSchoolPlan_ask(String schoolPlan_ask) {
		this.schoolPlan_ask = schoolPlan_ask;
	}
	public Date getSign_startTime() {
		return sign_startTime;
	}
	public void setSign_startTime(Date sign_startTime) {
		this.sign_startTime = sign_startTime;
	}
	public Date getSign_endTime() {
		return sign_endTime;
	}
	public void setSign_endTime(Date sign_endTime) {
		this.sign_endTime = sign_endTime;
	}
	public String getSign_method() {
		return sign_method;
	}
	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
	}
	  
	public static String[] getTblsteptitle() {
		return tblSchoolPlanTitle;
	}
	public static final String[] tblSchoolPlanTitle={"学校ID","学校名字","报名条件","报名开始时间","报名结束时间","报名方式"};
	
	public String getCell(int col){
		Session session=HibernateUtil.getSession();
		if(col==0)      return String.valueOf(this.school_id);
		else if(col==1) return ((BeanSchool)session.get(BeanSchool.class, this.school_id)).getSchool_name();
		else if(col==2) return this.schoolPlan_ask;
		else if(col==3) return String.valueOf(this.sign_startTime);
		else if(col==4) return String.valueOf(this.sign_endTime);
		else if(col==5) return String.valueOf(this.sign_method);
		else return "";
	} 
	  
}
