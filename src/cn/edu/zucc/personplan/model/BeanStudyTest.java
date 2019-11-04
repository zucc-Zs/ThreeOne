package cn.edu.zucc.personplan.model;

import org.hibernate.Session;

import cn.edu.zucc.personplan.util.HibernateUtil;

public class BeanStudyTest {
	private int studyTest_order;
	private String student_id;
	private int subject_id;
	private String studyTest_grade;
	public int getStudyTest_order() {
		return studyTest_order;
	}
	public void setStudyTest_order(int studyTest_order) {
		this.studyTest_order = studyTest_order;
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

	public String getStudyTest_grade() {
		return studyTest_grade;
	}
	public void setStudyTest_grade(String studyTest_grade) {
		this.studyTest_grade = studyTest_grade;
	}


	public static final String[] tblStudyTestTitle={"考生ID","科目Id","科目名","学考等级"};
	public String getCell(int col){
		Session session=HibernateUtil.getSession();
		if(col==0) return String.valueOf(this.student_id);
		else if(col==1) return String.valueOf(this.subject_id);
		else if(col==2) return ((BeanSubject)session.get(BeanSubject.class, this.subject_id)).getSubject_name();
		else if(col==3) return String.valueOf(this.studyTest_grade);
		else return "";
	}
	public static String[] getTabletitles() {
		return tblStudyTestTitle;
	}
}
