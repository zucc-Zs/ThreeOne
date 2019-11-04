package cn.edu.zucc.personplan.model;

import java.util.Date;
import java.util.Set;

import com.mysql.jdbc.Blob;

public class BeanStudent {
	private String student_id;//身份证号
	private int student_testid;//准考证号
	private String student_name;
	private String student_sex;
	private String student_phoneNumber;
	private String student_middleSchool;
	private String student_province;
	private Blob student_portrait;//头像

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public int getStudent_testid() {
		return student_testid;
	}

	public void setStudent_testid(int student_testid) {
		this.student_testid = student_testid;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_sex() {
		return student_sex;
	}

	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}

	public String getStudent_phoneNumber() {
		return student_phoneNumber;
	}

	public void setStudent_phoneNumber(String student_phoneNumber) {
		this.student_phoneNumber = student_phoneNumber;
	}

	public String getStudent_middleSchool() {
		return student_middleSchool;
	}

	public void setStudent_middleSchool(String student_middleSchool) {
		this.student_middleSchool = student_middleSchool;
	}

	public String getStudent_province() {
		return student_province;
	}

	public void setStudent_province(String student_province) {
		this.student_province = student_province;
	}

	public Blob getStudent_portrait() {
		return student_portrait;
	}

	public void setStudent_portrait(Blob student_portrait) {
		this.student_portrait = student_portrait;
	}

	
	public static final String[] tblStudentTitle={"身份证号","准考证号","姓名","性别","手机号码","所在中学","省份"};
	public String getCell(int col){
		if(col==0) return String.valueOf(this.student_id);
		else if(col==1) return String.valueOf(this.student_testid);
		else if(col==2) return this.student_name;
		else if(col==3) return this.student_sex;
		else if(col==4) return this.student_phoneNumber;
		else if(col==5) return this.student_middleSchool;
		else if(col==6) return this.student_province;
//		else if(col==7) return this.student_portrait;
		else return "";
	}
	public static String[] getTabletitles() {
		return tblStudentTitle;
	}
}
