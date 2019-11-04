package cn.edu.zucc.personplan.model;

import java.util.Set;

public class BeanSubject {
	private int subject_id;
	private String subject_name;
	private Set<BeanMajor> aMajors;
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public Set<BeanMajor> getaMajors() {
		return aMajors;
	}
	public void setaMajors(Set<BeanMajor> aMajors) {
		this.aMajors = aMajors;
	}
	
	public static final String[] tblSubjectTitle={"科目ID","科目名"};
	public String getCell(int col){
		if(col==0) return String.valueOf(this.subject_id);
		else if(col==1) return String.valueOf(this.subject_name);
		else return "";
	}
	public static String[] getTabletitles() {
		return tblSubjectTitle;
	}
}
