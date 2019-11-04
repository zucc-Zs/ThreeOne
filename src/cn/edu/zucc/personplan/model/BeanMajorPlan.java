package cn.edu.zucc.personplan.model;

import org.hibernate.Session;

import cn.edu.zucc.personplan.util.HibernateUtil;

public class BeanMajorPlan {	
	  private int majorPlan_order;
	  private int mojorPlan_year;
	  private int major_id;
	  private String selectSubject_name;
	  private String plan_num;
	  private String mojorPlan_tuition;
	  private String mojorPlan_ask;
	  private String mojorPlan_grade;
	  
	
	public int getMajorPlan_order() {
		return majorPlan_order;
	}
	public void setMajorPlan_order(int majorPlan_order) {
		this.majorPlan_order = majorPlan_order;
	}
	public int getMojorPlan_year() {
		return mojorPlan_year;
	}
	public void setMojorPlan_year(int mojorPlan_year) {
		this.mojorPlan_year = mojorPlan_year;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public String getSelectSubject_name() {
		return selectSubject_name;
	}
	public void setSelectSubject_name(String selectSubject_name) {
		this.selectSubject_name = selectSubject_name;
	}
	public String getPlan_num() {
		return plan_num;
	}
	public void setPlan_num(String plan_num) {
		this.plan_num = plan_num;
	}
	public String getMojorPlan_tuition() {
		return mojorPlan_tuition;
	}
	public void setMojorPlan_tuition(String mojorPlan_tuition) {
		this.mojorPlan_tuition = mojorPlan_tuition;
	}
	public String getMojorPlan_ask() {
		return mojorPlan_ask;
	}
	public void setMojorPlan_ask(String mojorPlan_ask) {
		this.mojorPlan_ask = mojorPlan_ask;
	}
	public String getMojorPlan_grade() {
		return mojorPlan_grade;
	}
	public void setMojorPlan_grade(String mojorPlan_grade) {
		this.mojorPlan_grade = mojorPlan_grade;
	}
	
	public static String[] getTblsteptitle() {
		return tblMajorPlanTitle;
	}
	public static final String[] tblMajorPlanTitle={"年份","专业编号","专业名字","选考科目","分数","计划数","学费","其他要求"};
	
	public String getCell(int col){
		Session session=HibernateUtil.getSession();
		if(col==0)      return String.valueOf(this.mojorPlan_year);
		else if(col==1) return String.valueOf(this.major_id);
		else if(col==2) return ((BeanMajor)session.get(BeanMajor.class, this.major_id)).getMajor_name();
		else if(col==3) return this.selectSubject_name;
		else if(col==4) return this.mojorPlan_grade;
		else if(col==5) return this.plan_num;
		else if(col==6) return this.mojorPlan_tuition;
		else if(col==7) return this.mojorPlan_ask;
		else return "";
	} 
}
