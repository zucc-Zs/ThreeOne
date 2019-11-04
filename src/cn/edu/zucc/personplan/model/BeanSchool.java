package cn.edu.zucc.personplan.model;

import java.util.Set;

public class BeanSchool {

	private int school_id;
	private String school_name;
	private String school_province;
	private String school_city;
	private String school_evaluate;//学校描述
	private String school_advantage;
	private Set<BeanMajor> aMajors;
	
	

	public Set<BeanMajor> getaMajors() {
		return aMajors;
	}
	public void setaMajors(Set<BeanMajor> aMajors) {
		this.aMajors = aMajors;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_province() {
		return school_province;
	}
	public void setSchool_province(String school_province) {
		this.school_province = school_province;
	}
	public String getSchool_city() {
		return school_city;
	}
	public void setSchool_city(String school_city) {
		this.school_city = school_city;
	}
	public String getSchool_evaluate() {
		return school_evaluate;
	}
	public void setSchool_evaluate(String school_evaluate) {
		this.school_evaluate = school_evaluate;
	}
	public String getSchool_advantage() {
		return school_advantage;
	}
	public void setSchool_advantage(String school_advantage) {
		this.school_advantage = school_advantage;
	}
	
	public static final String[] tblSchoolTitle={"学校id","学校名称","省份","城市","学校描述","优势专业"};
	
	
	public String getCell(int col){
		if(col==0) return String.valueOf(this.school_id);
		else if(col==1) return this.school_name;
		else if(col==2) return this.school_province;
		else if(col==3) return this.school_city;
		else if(col==4) return this.school_evaluate;
		else if(col==5) return this.school_advantage;
		else return "";
	}
	public static String[] getTabletitles() {
		return tblSchoolTitle;
	}
}
