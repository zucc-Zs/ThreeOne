package cn.edu.zucc.personplan.model;

import org.hibernate.Session;

import cn.edu.zucc.personplan.util.HibernateUtil;

public class BeanSelectTest {
	private int selectTest_order;
	private String student_id;
	private int subject_id;
	private String subject_name;
	private String selectTest_grade;
	
	public int getSelectTest_order() {
		return selectTest_order;
	}
	public void setSelectTest_order(int studyTest_order) {
		this.selectTest_order = studyTest_order;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSelectTest_grade() {
		return selectTest_grade;
	}
	public void setSelectTest_grade(String selectTest_grade) {
		this.selectTest_grade = selectTest_grade;
	}
	
	public static final String[] tblSelectTestTitle={"考生ID","科目Id","科目名","选考成绩"};
	public String getCell(int col){
		Session session=HibernateUtil.getSession();
		if(col==0) return String.valueOf(this.student_id);
		else if(col==1) return String.valueOf(this.subject_id);
		else if(col==2) return ((BeanSubject)session.get(BeanSubject.class, this.subject_id)).getSubject_name();
		else if(col==3) return String.valueOf(this.selectTest_grade);
		else return "";
	}
	public static String[] getTabletitles() {
		return tblSelectTestTitle;
	}
}
