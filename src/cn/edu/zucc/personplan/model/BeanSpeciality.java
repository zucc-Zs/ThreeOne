package cn.edu.zucc.personplan.model;

import org.hibernate.Session;

import cn.edu.zucc.personplan.util.HibernateUtil;

public class BeanSpeciality {
		private int speciality_order; 
		private String student_id;
		private String speciality_type;
		private String speciality_grade;
		private String speciality_evidence;
		
		
		public int getSpeciality_order() {
			return speciality_order;
		}
		public void setSpeciality_order(int speciality_order) {
			this.speciality_order = speciality_order;
		}
		public String getStudent_id() {
			return student_id;
		}
		public void setStudent_id(String student_id) {
			this.student_id = student_id;
		}
		public String getSpeciality_type() {
			return speciality_type;
		}
		public void setSpeciality_type(String speciality_type) {
			this.speciality_type = speciality_type;
		}
		public String getSpeciality_grade() {
			return speciality_grade;
		}
		public void setSpeciality_grade(String speciality_grade) {
			this.speciality_grade = speciality_grade;
		}
		public String getSpeciality_evidence() {
			return speciality_evidence;
		}
		public void setSpeciality_evidence(String speciality_evidence) {
			this.speciality_evidence = speciality_evidence;
		}
		
		public static String[] getTblsteptitle() {
			return tblSpecialityTitle;
		}
		public static final String[] tblSpecialityTitle={"学生ID","学生姓名","特长类别","特长等级","佐证材料"};
		
		public String getCell(int col){
			Session session=HibernateUtil.getSession();
			if(col==0)      return String.valueOf(this.student_id);
			else if(col==1) return ((BeanStudent)session.get(BeanStudent.class, this.student_id)).getStudent_name();
			else if(col==2) return this.speciality_type;
			else if(col==3) return this.speciality_grade;
			else if(col==4) return this.speciality_evidence;
			else return "";
		}
}
