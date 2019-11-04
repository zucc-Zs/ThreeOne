package cn.edu.zucc.personplan.model;

import org.hibernate.Session;

import cn.edu.zucc.personplan.util.HibernateUtil;

public class BeanRecommend { 
	private int recommend_order;
	private String student_id;
	private int major_id;
	private String recommend_reason;
	
	public int getRecommend_order() {
		return recommend_order;
	}
	public void setRecommend_order(int recommend_order) {
		this.recommend_order = recommend_order;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public String getRecommend_reason() {
		return recommend_reason;
	}
	public void setRecommend_reason(String recommend_reason) {
		this.recommend_reason = recommend_reason;
	}

	public static String[] getTblsteptitle() {
		return tblRecommendTitle;
	}
	public static final String[] tblRecommendTitle={"身份证号","学生姓名","专业ID","专业名称","推荐理由"};
	
	public String getCell(int col){
		Session session=HibernateUtil.getSession();
		if(col==0)      return this.student_id;
		else if(col==1) return ((BeanStudent)session.get(BeanStudent.class, this.student_id)).getStudent_name();
		else if(col==2) return String.valueOf(this.major_id);
		else if(col==3) return ((BeanMajor)session.get(BeanMajor.class, this.major_id)).getMajor_name();
		else if(col==4) return this.recommend_reason;
		else return "";
	}
	  
}
